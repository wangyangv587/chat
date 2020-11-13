package com.shadow.chat.bean.client;

import cn.hutool.json.JSONUtil;
import com.shadow.chat.bean.constant.Chat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 向单个用户发送数据
 *
 * @author Shadow
 * @date 2020/11/02 14:01:40
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ReqSingleDataDTO extends ReqData implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReqSingleDataDTO() {
        this(Chat.MsgType.TEXT);
    }

    public ReqSingleDataDTO(Integer msgType) {
        this.msgType = msgType;
    }

    public ReqSingleDataDTO(String targetUserAccount, String msg, Integer msgType) {
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
