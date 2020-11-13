package com.shadow.chat.user.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) APPLICATION_CONTEXT.getBean(clazz);
    }

    public static Object getBean(String beanName) {
        return APPLICATION_CONTEXT.getBean(beanName);
    }
}
