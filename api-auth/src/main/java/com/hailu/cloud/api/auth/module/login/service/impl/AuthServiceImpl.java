package com.hailu.cloud.api.auth.module.login.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.api.auth.module.login.dao.MemberMapper;
import com.hailu.cloud.api.auth.module.login.dao.MerchantMapper;
import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.exception.RefreshTokenExpiredException;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.AuthInfoParseTool;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhijie
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private RedisStandAloneClient redisClient;

    /**
     * 心安会员账号/商城会员账号
     */
    @Resource
    private MemberMapper memberMapper;

    /**
     * 商户账号
     */
    @Resource
    private MerchantMapper merchantMapper;

    /**
     * 是否启用全局万能验证码
     */
    @Value("${dev.enable.global-veri-code:false}")
    private boolean enableGlobalVeriCode;

    @Override
    public String refreshAccessToken(String refreshToken) throws RefreshTokenExpiredException, BusinessException {
        // 验证token是否有效
        DecodedJWT decodedJwt = JwtUtil.verifierToken(refreshToken);
        if (decodedJwt == null) {
            throw new BusinessException("无效的refreshToken");
        }
        // 根据token获取redis value
        String token = decodedJwt.getClaim(Constant.JWT_ACCESS_TOKEN).asString();
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + token;
        String redisUserInfoJsonValue = redisClient.stringGet(refreshTokenRedisKey);
        if (StringUtils.isBlank(redisUserInfoJsonValue)) {
            throw new RefreshTokenExpiredException("RefreshToken已失效，请重新登录");
        }
        // 判断token是否过期
        AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, decodedJwt);
        Date current = new Date();
        Date expire = DateUtil.date(authInfo.getRefreshTokenExpire());
        if (DateUtil.compare(expire, current) < 0) {
            throw new RefreshTokenExpiredException("RefreshToken已失效，请重新登录");
        }
        // 更新token
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        redisClient.deleteKey(Constant.REDIS_KEY_AUTH_INFO + authInfo.getAccessToken());
        String accessToken = IdUtil.simpleUUID();
        authInfo.setAccessToken(JwtUtil.createToken(accessToken, 0));
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());
        AuthInfoParseTool.updateAuthInfoToken(authInfo, authInfo.getAccessToken(), null);

        // 存储到redis,并设置有效期
        String authJson = JSON.toJSONString(authInfo);
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        redisClient.stringSet(accessTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        redisClient.stringSet(refreshTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_SEVEN_DAYS);
        return authInfo.getAccessToken();
    }

    @Override
    public Object vericodeLogin(Integer loginType, String phone, String code) throws BusinessException {
        if (0 == loginType) {
            // 心安&商城用户验证码登录
            return vericodeLoginHandle(phone, code, new IAccountCallback() {

                private MemberLoginInfoModel userInfo;

                @Override
                public String queryAccountUserId(String phone) {
                    userInfo = memberMapper.findMember(phone);
                    return userInfo == null ? null : userInfo.getUserId();
                }

                @Override
                public Object handle(String accessToken, String refreshToken) {
                    userInfo.setAccessToken(accessToken);
                    userInfo.setRefreshToken(refreshToken);
                    return userInfo;
                }
            });
        }
        if (1 == loginType) {
            // 商户用户验证码登录
            return vericodeLoginHandle(phone, code, new IAccountCallback() {

                private MerchantUserLoginInfoModel userInfo;

                @Override
                public String queryAccountUserId(String phone) {
                    userInfo = merchantMapper.findUser(phone);
                    return userInfo == null ? null : userInfo.getNumberid();
                }

                @Override
                public Object handle(String accessToken, String refreshToken) {
                    userInfo.setAccessToken(accessToken);
                    userInfo.setRefreshToken(refreshToken);
                    return userInfo;
                }
            });
        }
        throw new BusinessException("不允许该用户登录");
    }

    @Override
    public void logout(String refreshToken) throws BusinessException {
        DecodedJWT decodedJwt = JwtUtil.verifierToken(refreshToken);
        if (decodedJwt == null) {
            throw new BusinessException("无效的refreshToken");
        }
        String token = decodedJwt.getClaim(Constant.JWT_ACCESS_TOKEN).asString();
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + token;
        String redisUserInfoJsonValue = redisClient.stringGet(refreshTokenRedisKey);
        if (StringUtils.isBlank(redisUserInfoJsonValue)) {
            return;
        }

        AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, decodedJwt);
        if (authInfo == null) {
            return;
        }
        // 清理redis缓存
        String redisToken = JwtUtil.extractToken(authInfo.getAccessToken(), Constant.JWT_ACCESS_TOKEN);
        if (redisToken == null) {
            return;
        }
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + redisToken;
        redisClient.deleteKey(accessTokenRedisKey, refreshTokenRedisKey);
    }

    interface IAccountCallback {

        /**
         * 查询用户ID
         *
         * @param phone
         * @return
         */
        String queryAccountUserId(String phone);

        /**
         * 将生成的token保存到用户信息返回给客户端
         *
         * @param accessToken
         * @param refreshToken
         * @return
         */
        Object handle(String accessToken, String refreshToken);

    }

    /**
     * 验证码登录处理
     *
     * @param phone    手机号
     * @param code     验证码
     * @param callback 账号数据查询接口
     * @return
     * @throws BusinessException
     */
    private Object vericodeLoginHandle(String phone, String code, IAccountCallback callback) throws BusinessException {
        if (!enableGlobalVeriCode) {
            String vericodeRedisKey = Constant.REDIS_KEY_VERIFICATION_CODE + phone;
            String redisCode = redisClient.stringGet(vericodeRedisKey);
            if (!code.equals(redisCode)) {
                throw new BusinessException("验证码不正确或已过期");
            }
            // 删除redis里的验证码
            redisClient.deleteKey(vericodeRedisKey);
        }
        String userId = callback.queryAccountUserId(phone);
        if (StringUtils.isBlank(userId)) {
            throw new BusinessException("该手机号码尚未注册");
        }
        // 生成认证信息存储于redis, accessToken有效期2小时， refreshToken有效期7天
        Date current = new Date();
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUserId(userId);

        String accessToken = IdUtil.simpleUUID();
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        authInfo.setAccessToken(JwtUtil.createToken(accessToken, 0));
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());

        String refreshToken = IdUtil.simpleUUID();
        Date refreshTokenExpire = DateUtil.offset(current, DateField.DAY_OF_MONTH, 7);
        authInfo.setRefreshToken(JwtUtil.createToken(refreshToken, 0));
        authInfo.setRefreshTokenExpire(refreshTokenExpire.getTime());

        Object userInfo = callback.handle(authInfo.getAccessToken(), authInfo.getRefreshToken());
        authInfo.setUserInfo(userInfo);

        // 存储到redis,并设置有效期
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + refreshToken;
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        String authJson = JSON.toJSONString(authInfo);
        redisClient.stringSet(accessTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        redisClient.stringSet(refreshTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_SEVEN_DAYS);
        return userInfo;
    }

}
