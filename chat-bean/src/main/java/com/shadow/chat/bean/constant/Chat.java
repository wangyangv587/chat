package com.shadow.chat.bean.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
public class Chat {

    private Chat() {
    }

    public static final String DELIMITER = "\0";

    public static class Cmd {
        //登录
        public static final String LOGIN = "LOGIN";
        //发送消息到单个人
        public static final String SINGLE = "SINGLE";
        //发送群消息
        public static final String GROUP = "GROUP";
        //系统通知
        public static final String SYSTEM = "OK";
    }

    /**
     * 消息类型
     */
    public static class MsgType{
        /** 纯文本 */
        public static final int TEXT = 1;
        /** 音乐 */
        public static final int MUSIC = 2;
        /** 视频 */
        public static final int VIDEO = 3;
        /** 外部链接 */
        public static final int LINK = 4;

    }

    /**
     * 命令列表
     */
    public static List<String> CMD_LIST = new ArrayList<>(3);

}
