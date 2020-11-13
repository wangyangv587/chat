package com.shadow.chat.user.service;

import cn.hutool.json.JSONObject;
import com.shadow.chat.client.Client;
import com.shadow.chat.user.config.ChatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Shadow
 * @date 2020/11/02 16:05:49
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private ChatConfig chatConfig;

    public JSONObject start(String userAccount) {

        JSONObject jsonObject = new JSONObject();
        try {
            new Thread(() -> {
                Client.run(chatConfig.getServerHost(), chatConfig.getServerPort(), userAccount);
            }).start();
        } catch (Exception e) {
            jsonObject.set("msg", "登录失败");
            jsonObject.set("code", 500);
            log.error("start | 启动客户端出错：{}",e.getMessage(),e);
            return jsonObject;
        }
        jsonObject.set("msg", "登录成功");
        jsonObject.set("code", 200);
        return jsonObject;
    }
}
