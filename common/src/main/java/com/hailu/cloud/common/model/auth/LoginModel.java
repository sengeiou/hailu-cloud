package com.hailu.cloud.common.model.auth;

import lombok.Data;

/**
 * @author xuzhijie
 */
@Data
public class LoginModel {

    /**
     * access token
     */
    private String accessToken;

    /**
     * refresh token
     */
    private String refreshToken;

}