package com.shadow.chat.bean.client;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@Setter
@Getter
public class ReqMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String cmd;

    private String channelId;

    private ReqData data;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
