package com.imooc.controller;

import com.imooc.bo.RegistLoginBO;
import com.imooc.grace.result.GraceJSONResult;
import com.imooc.grace.result.ResponseStatusEnum;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.IPUtil;
import com.imooc.utils.SMSUtils;
import com.imooc.vo.UsersVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

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
public class PassportController extends BaseInfoProperties {
    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private UserService userService;

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

        redisOperator.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);
        return GraceJSONResult.ok();
    }

    @PostMapping("login")
    public GraceJSONResult login(@Valid @RequestBody RegistLoginBO registLoginBO,
//                                 BindingResult result, // 对代码有侵入性
                                 HttpServletRequest request) throws Exception {
        // 0.BindingResult 保存了错误的验证信息，要返回给前端
//        if (result.hasErrors()) {
//            Map<String, String> map = getErrors(result);
//            return GraceJSONResult.errorMap(map);
//        }
        String mobile = registLoginBO.getMobile();
        String code = registLoginBO.getSmsCode();

        // 1.验证码匹配与否
        String redisCode = redisOperator.get(MOBILE_SMSCODE + ":" + mobile);
        if (StringUtils.isBlank(redisCode) || !redisCode.equalsIgnoreCase(code)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }
        // 2.查数据库，看用户存在与否
        Users user = userService.queryMobileIsExist(mobile);
        if (user == null) {
            // 用户为空代表没有注册过
            user = userService.createUser(mobile);
        }
        // 3.保存用户会话信息
        String uToken = UUID.randomUUID().toString();
        redisOperator.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);
        // 4.用户登录注册成功后，删掉 Redis 中的验证码
        redisOperator.del(MOBILE_SMSCODE + ":" + mobile);
        // 5.返回用户信息给前端，带 token 令牌
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user, usersVO);
        usersVO.setUserToken(uToken);
        // TODO 为什么前端也要 token？

        return GraceJSONResult.ok(usersVO);
    }

    @PostMapping("logout")
    public GraceJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request) throws Exception {

        // 后端只需要清除用户的token信息即可，前端也需要清除，清除本地app中的用户信息和token会话信息
        redisOperator.del(REDIS_USER_TOKEN + ":" + userId);

        return GraceJSONResult.ok();
    }

}
