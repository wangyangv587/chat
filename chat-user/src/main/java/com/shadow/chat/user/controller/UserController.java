package com.shadow.chat.user.controller;

import cn.hutool.json.JSONObject;
import com.shadow.chat.user.service.UserService;
import com.shadow.chat.user.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public JSONObject login(@RequestBody UserLoginVO user){

        return userService.start(user.getUserAccount());
    }
}
