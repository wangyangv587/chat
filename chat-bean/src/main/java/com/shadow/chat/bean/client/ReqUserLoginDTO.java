package com.shadow.chat.bean.client;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/** 登录数据
 * @author Shadow
 * @date 2020/11/02 14:01:26
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ReqUserLoginDTO extends ReqData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String nickName;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
