package com.shadow.chat.bean;

import cn.hutool.json.JSONUtil;

import java.io.Serializable;

/**
 * @author Shadow
 * @date 2021/01/22 17:02:00
 */
public class User implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
