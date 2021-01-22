package com.shadow.chat.bean;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author Shadow
 * @date 2021/01/22 15:53:30
 */
public class Message {

    //数据长度
    private int length;
    //数据内容
    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
