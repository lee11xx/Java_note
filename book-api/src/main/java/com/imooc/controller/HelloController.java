package com.imooc.controller;

import com.imooc.grace.result.GraceJSONResult;
import com.imooc.utils.SMSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lee
 * @description:
 * @package: com.imooc.controller
 * @date: Created in 2022/12/26 19:21
 */

@Slf4j
@Api(tags = "hello 测试的接口")
@RestController
public class HelloController {
    @Autowired
    private SMSUtils smsUtils;

    @ApiOperation(value = "hello - 这是一个hello的测试路由")
    @GetMapping("hello")
    public Object hello() {
        return GraceJSONResult.ok();
    }

    @ApiOperation(value = "sms")
    @GetMapping("sms")
    public Object sms() throws Exception {
        String code = "343242";
        smsUtils.sendSMS("17608467179", code);

        return GraceJSONResult.ok();
    }
}
