package com.shadow.chat.client;

import com.shadow.chat.bean.Message;
import com.shadow.chat.bean.User;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * @author Shadow
 * @date 2021/01/26 17:31:07
 */
public class CallTask implements Callable<Object> {

    private User user;

    public CallTask(User user) {
        this.user = user;
    }

    private static CountDownLatch c;
    private static ConcurrentHashMap<Long, Object> map = new ConcurrentHashMap();
    public static final int id = 1;


    @Override
    public Object call() throws Exception {
        c = new CountDownLatch(1);
        byte[] bytes = user.toString().getBytes(StandardCharsets.UTF_8);
        Message message = new Message();
        message.setLength(bytes.length);
        message.setContent(bytes);
        Client.channel.writeAndFlush(message);
        c.await(10000, TimeUnit.MILLISECONDS);
        Object o = map.get(user.getId());
        if (o == null) {
            throw new TimeoutException("等待超时消息【" + user.getName() + "】响应超时");
        }
        return o;
    }

    public static void countDown() {
        c.countDown();
    }

    public static void putValue(Long key, Object data) {
        map.put(key, data);
    }
}
