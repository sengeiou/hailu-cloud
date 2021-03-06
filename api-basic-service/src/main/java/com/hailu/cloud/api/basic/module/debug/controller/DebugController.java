package com.hailu.cloud.api.basic.module.debug.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "调试")
@RequestMapping("/debug")
public class DebugController {

    @Resource
    private RedisStandAloneClient redisClient;

    @ApiOperation(value = "模拟token失效", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': null\n" +
            "}" +
            "</pre>")
    @GetMapping("/invalid/token")
    public void invalidToken(String accessToken, String refreshToken) throws BusinessException {
        {
            if (StringUtils.isNotBlank(accessToken)) {
                // 验证token是否有效
                DecodedJWT decodedJwt = JwtUtil.verifierToken(accessToken);
                if (decodedJwt == null) {
                    throw new BusinessException("无效的accessToken");
                }
                String token = decodedJwt.getClaim(Constant.JWT_TOKEN).asString();
                String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + token;
                redisClient.deleteKey(accessTokenRedisKey);
            }
        }
        {
            if (StringUtils.isNotBlank(refreshToken)) {
                // 验证token是否有效
                DecodedJWT decodedJwt = JwtUtil.verifierToken(refreshToken);
                if (decodedJwt == null) {
                    throw new BusinessException("无效的refreshToken");
                }
                String token = decodedJwt.getClaim(Constant.JWT_TOKEN).asString();
                String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + token;
                redisClient.deleteKey(refreshTokenRedisKey);
            }
        }
    }

    @ApiOperation(value = "解析token", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': null\n" +
            "}" +
            "</pre>")
    @GetMapping("/parse/token")
    public List<Map<String, Object>> parseToken(String accessToken, String refreshToken) throws BusinessException {
        List<Map<String, Object>> parseInfo = new ArrayList<>(2);

        {
            if (StringUtils.isNotBlank(accessToken)) {
                // 验证token是否有效
                DecodedJWT decodedJwt = JwtUtil.verifierToken(accessToken);
                if (decodedJwt == null) {
                    throw new BusinessException("无效的accessToken");
                }
                Map<String, Object> item = new HashMap<>();
                item.put("type", "accessToken");
                item.put(Constant.JWT_TOKEN, decodedJwt.getClaim(Constant.JWT_TOKEN).asString());
                item.put(Constant.JWT_LOGIN_TYPE, decodedJwt.getClaim(Constant.JWT_LOGIN_TYPE).asInt());
                parseInfo.add(item);
            }
        }
        {
            if (StringUtils.isNotBlank(refreshToken)) {
                // 验证token是否有效
                DecodedJWT decodedJwt = JwtUtil.verifierToken(refreshToken);
                if (decodedJwt == null) {
                    throw new BusinessException("无效的refreshToken");
                }
                Map<String, Object> item = new HashMap<>();
                item.put("type", "refreshToken");
                item.put(Constant.JWT_TOKEN, decodedJwt.getClaim(Constant.JWT_TOKEN).asString());
                item.put(Constant.JWT_LOGIN_TYPE, decodedJwt.getClaim(Constant.JWT_LOGIN_TYPE).asInt());
                parseInfo.add(item);
            }
        }

        return parseInfo;
    }

}
