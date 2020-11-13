package com.shadow.chat.bean.server;

import cn.hutool.json.JSONUtil;
import com.shadow.chat.bean.constant.Chat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Shadow
 * @date 2020/11/02 15:11:32
 */
@Getter
@Setter
public class RespSystemDataDTO extends RespData implements Serializable {

    public RespSystemDataDTO() {
        this.msgType = Chat.MsgType.TEXT;
    }

    public RespSystemDataDTO(String msg, Integer msgType) {
        this.msg = msg;
        this.msgType = msgType;
    }

    public RespSystemDataDTO(Integer msgType) {
        this.msgType = msgType;
    }

    private static final long serialVersionUID = 1L;

    private String msg;

    private Integer msgType;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
