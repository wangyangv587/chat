package com.shadow.chat.user.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@ComponentScan("com.shadow.chat")
@Configuration
@Getter
public class ChatConfig {

    @Value("${chat.server.host:localhost}")
    private String serverHost;

    @Value("${chat.server.port:8080}")
    private int serverPort;
}
