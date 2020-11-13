package com.shadow.chat.bean.server;

import cn.hutool.json.JSONUtil;
import com.shadow.chat.bean.constant.Chat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应下发数据
 *
 * @author Shadow
 * @date 2020/11/02 15:26:12
 */
@Getter
@Setter
public class RespSingleDataDTO extends RespData implements Serializable {

    private static final long serialVersionUID = 1L;

    public RespSingleDataDTO() {
        this(Chat.MsgType.TEXT);
    }

    public RespSingleDataDTO(Integer msgType) {
        this.msgType = msgType;
    }

    public RespSingleDataDTO(String targetUserAccount, String msg, Integer msgType) {
        this.targetUserAccount = targetUserAccount;
        this.msg = msg;
        this.msgType = msgType;
    }

    /**
     * 目标账号id
     */
    private String targetUserAccount;

    /**
     * 发送账号id
     */
    private String userAccount;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 消息类型
     */
    private Integer msgType;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
