package com.imooc.controller;

import com.imooc.grace.result.GraceJSONResult;
import com.imooc.utils.IPUtil;
import com.imooc.utils.SMSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Lee
 * @description:
 * @package: com.imooc.controller
 * @date: Created in 2022/12/26 19:21
 */

@Slf4j
@Api(tags = "PassportController 通信证接口模块")
@RequestMapping("passport")
@RestController
public class PassportInfoProperties extends BaseInfoProperties {
    @Autowired
    private SMSUtils smsUtils;

    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(@RequestParam String mobile,
                             HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(mobile)) {
            return GraceJSONResult.ok();
        }
        // 获得用户IP
        String userIp = IPUtil.getRequestIp(request);
        // 根据IP进行限制，用户60秒内只能获得一次验证码
        redisOperator.setnx60s(MOBILE_SMSCODE + ":" + userIp, userIp);
//        String code = (int)Math.random() * 1000000 + "";
        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
        smsUtils.sendSMS(mobile, code);
        log.info(code);

        // TODO 把验证码放到 Redis，用于后续的验证
        redisOperator.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);
        return GraceJSONResult.ok();
    }
}
