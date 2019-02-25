package com.sise.house.admin.app.support;


import com.sise.common.util.CharUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Token管理类
 * @Auther: 冲之
 * @Date: 2019/1/6 22:47
 */
public class TokenManager {
    private static Map<String, Token> tokenMap = new HashMap<>();
    private static Map<Long, Token> idMap = new HashMap<>();

    public static Long getUserId(String token) {

        Token userToken = tokenMap.get(token);
        if (userToken == null) {
            return null;
        }
        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            return null;
        }
        return userToken.getUserId();
    }


    public static Token generateToken(Long id) {
        Token userToken = null;
        String token = CharUtil.getRandomString(32);
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }
        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);
        userToken = new Token();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);
        return userToken;
    }

    public static void logout(String token) {
        Token userToken = tokenMap.get(token);
        if (null != userToken) {
            tokenMap.remove(token);
            Long userId = getUserId(token);
            if (userId != null) {
                idMap.remove(userId);
            }
        }
    }
}

