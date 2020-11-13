package com.shadow.chat.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Shadow
 * @date 2020/11/02 13:41:22
 */
@Getter
@Setter
public class UserVO implements Serializable{

    private String userAccount;

    private String nickName;

}
