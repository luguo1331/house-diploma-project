package com.sise.common.util;

import com.google.common.hash.Hashing;

/**
 * @Description: MD5 工具类
 * @Auther: chong
 * @Date: 2018/12/2 22:24
 */
public class MD5Util {

    public static String MD5String(String input, String salt) {
        input = input + salt;
        return Hashing.md5().hashBytes(input.getBytes()).toString();
    }
}
