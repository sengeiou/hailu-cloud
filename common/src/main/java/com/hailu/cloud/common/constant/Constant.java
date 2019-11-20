package com.hailu.cloud.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Author xuzhijie
 * @Date 2019/11/6 11:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

    // region redis key 前缀

    /**
     * 验证码前缀，用于保存验证码
     * 如：
     * VERICODE:13825693085
     * VERICODE:13825693086
     */
    public static final String REDIS_KEY_VERIFICATION_CODE = "VERICODE:";

    /**
     * 认证信息前缀，用于存储用户登录信息
     * 如：
     * AUTH_INFO:5775b772b5cb4206bdc74f3cb74eaaf3
     * AUTH_INFO:5f3b4d496062443b83feccb67d7733ac
     */
    public static final String REDIS_KEY_AUTH_INFO = "AUTH_INFO:";

    /**
     * redis key 7天有效期
     */
    public static final int REDIS_EXPIRE_OF_SEVEN_DAYS = 604800;

    /**
     * redis key 2小时有效期
     */
    public static final int REDIS_EXPIRE_OF_TWO_HOUR = 7200;

    /**
     * token存储，用于存储refreshToken与accessToken关联
     * 如：
     * TOKEN_STORE:5775b772b5cb4206bdc74f3cb74eaaf3
     * TOKEN_STORE:5f3b4d496062443b83feccb67d7733ac
     */
    public static final String REDIS_KEY_REFRESH_TOKEN_STORE = "TOKEN_STORE:";

    /**
     * 字典存储
     * 如：
     * DICT_CACHE:ORDER_STATUS
     * DICT_CACHE:TO_EXAMINE
     */
    public static final String REDIS_KEY_DICT_CACHE = "DICT_CACHE:";

    // endregion

    // region request header

    /**
     * 网关设置到请求头里面
     */
    public static final String REQUEST_HEADER_GATEWAY_USER_INFO = "GATEWAY_USER_INFO";

    /**
     * 请求头里的Access-token
     */
    public static final String REQUEST_HEADER_ACCESS_TOKEN = "Access-token";

    /**
     * 请求头里的Content-Type
     */
    public static final String REQUEST_HEADER_CONTENT_TYPE = "Content-Type";

    // endregion

    // region request attribute

    /**
     * 网关设置到请求头里面
     */
    public static final String REQUEST_ATTRIBUTE_CURRENT_USER = "CURRENT_USER";

    // endregion

    // region jwt const

    /**
     * jwt 用户登录类型
     */
    public static final String JWT_LOGIN_TYPE = "LOGIN_USER_TYPE";

    /**
     * jwt 用户token
     */
    public static final String JWT_ACCESS_TOKEN = "ACCESS_TOKEN";

    // endregion

    // region gateway

    /**
     * gateway router key
     */
    public static final String GATEWAY_ROUTER_KEY = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute";

    // endregion

    // region 版本&服务名称

    public static final String API_VERSION_V1 = "/api/v1";
    public static final String API_NAME_BASIC = "/basic";

    // endregion

}
