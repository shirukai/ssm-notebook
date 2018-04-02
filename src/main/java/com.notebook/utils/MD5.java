package com.notebook.utils;

import org.springframework.util.DigestUtils;

/**
 * 生成MD5
 * Created by shirukai on 2017/11/13.
 */
public class MD5 {
    //md5盐值字符串，用户混淆MD5
    private static String slat = "df%$4&df*(f&*@((#)3sdf!#$%^&*";
    public static String get(String arg){
        String base = slat+arg;
        return  DigestUtils.md5DigestAsHex(base.getBytes());
    }
    public static String get(){
        String base = slat;
        return  DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
