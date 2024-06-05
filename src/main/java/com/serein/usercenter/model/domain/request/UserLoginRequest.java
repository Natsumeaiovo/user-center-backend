package com.serein.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author serein
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 2411577210242619273L;

    private String userAccount;

    private String userPassword;

}
