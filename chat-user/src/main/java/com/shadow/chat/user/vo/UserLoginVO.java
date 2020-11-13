package com.shadow.chat.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@Getter
@Setter
public class UserLoginVO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String validCode;
}
