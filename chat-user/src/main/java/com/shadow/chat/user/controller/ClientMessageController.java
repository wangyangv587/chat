package com.shadow.chat.user.controller;

import cn.hutool.json.JSONObject;
import com.shadow.chat.bean.client.ReqMessage;
import com.shadow.chat.bean.client.ReqSingleDataDTO;
import com.shadow.chat.bean.constant.Chat;
import com.shadow.chat.client.Client;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@RestController
@RequestMapping("client")
public class ClientMessageController {

    public static final Map<String,Channel> tokenMap = new ConcurrentHashMap<>();

    /**
     * 发送单个消息
     * @param jsonObject
     * @return
     */
    @PostMapping("single")
    public String sendMsg(@RequestBody JSONObject jsonObject) {

        String userAccount = jsonObject.getStr("userAccount");
        String targetUserAccount = jsonObject.getStr("targetUserAccount");
        String msg = jsonObject.getStr("msg");
        ReqMessage req = new ReqMessage();
        ReqSingleDataDTO reqSingleDataDTO = new ReqSingleDataDTO();
        reqSingleDataDTO.setMsg(msg);
        reqSingleDataDTO.setTargetUserAccount(targetUserAccount);
        reqSingleDataDTO.setUserAccount(userAccount);
        req.setData(reqSingleDataDTO);
        req.setCmd(Chat.Cmd.SINGLE);
        Client.sendMsg(req);

        return "succeed";
    }


}
