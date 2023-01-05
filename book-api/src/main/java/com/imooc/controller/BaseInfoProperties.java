package com.imooc.controller;

import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Lee
 * @description:
 * @package: com.imooc.controller
 * @date: Created in 2023/1/5 10:52
 */
public class BaseInfoProperties {
    @Autowired
    public RedisOperator redisOperator;

    public static final String MOBILE_SMSCODE = "mobile:smscode";
    public static final String REDIS_USER_TOKEN = "redis_user_token";
    public static final String REDIS_USER_INFO = "redis_user_info";
}
