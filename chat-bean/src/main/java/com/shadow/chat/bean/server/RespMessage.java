package com.shadow.chat.bean.server;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@Getter
@Setter
public class RespMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String cmd;

    private RespData data;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
