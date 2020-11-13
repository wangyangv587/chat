package com.shadow.chat.server;

/**
 * @author Shadow
 * @date 2020/11/09 18:02:44
 */
public class StringUtils {

    public static String hexbytes2String(byte[] bytes, String separate) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
            sb.append(separate);
        }
        return sb.toString();
    }
}
