package com.shadow.chat.user.config;

import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Shadow
 * @date 2020/11/02 16:14:53
 */
@RestControllerAdvice
public class CommExceptionHandler {

    @ExceptionHandler(Exception.class)
    public JSONObject handlerException(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("code",500);
        jsonObject.set("sg","服务器异常，请稍后再试！");

        return jsonObject;
    }
}
