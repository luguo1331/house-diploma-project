package com.sise.house.comment.biz.Service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 邮件 Service
 * @Auther: 冲之
 * @Date: 2019/2/18 01:52
 */
@Service
public class EmailService {
    @Resource
    private MailService mailService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    /**
     * 发送邮件验证码
     *
     * @param email
     */
    public void codeNotify(String email) {
        String randomKey = RandomStringUtils.randomAlphabetic(4);
        redisTemplate.opsForValue().set(randomKey, email);
        redisTemplate.expire(randomKey, 1, TimeUnit.HOURS);
        mailService.sendSimpleMail("房产销售平台验证码", "key=" + randomKey + ",过期时间一个小时", email);
    }

    /**
     * 根据key邮箱
     *
     * @param key
     * @return
     */
    public String getKeyEmail(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
