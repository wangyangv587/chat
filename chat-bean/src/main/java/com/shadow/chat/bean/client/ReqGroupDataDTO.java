package com.shadow.chat.bean.client;

import cn.hutool.json.JSONUtil;
import com.shadow.chat.bean.constant.Chat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 向群聊发送数据
 *
 * @author Shadow
 * @date 2020/11/02 14:01:49
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ReqGroupDataDTO extends ReqData implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReqGroupDataDTO() {
        this(Chat.MsgType.TEXT);
    }

    public ReqGroupDataDTO(Integer msgType) {

        this.msgType = msgType;
    }

    public ReqGroupDataDTO(String targetGroupId, String msg, Integer msgType) {
        this.targetGroupId = targetGroupId;
        this.msg = msg;
        this.msgType = msgType;
    }

    /**
     * 目标群聊id
     */
    private String targetGroupId;

    /**
     * 用户账号
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
