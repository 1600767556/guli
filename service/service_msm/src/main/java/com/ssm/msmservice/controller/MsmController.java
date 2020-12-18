package com.ssm.msmservice.controller;

import com.ssm.commonutils.R;
import com.ssm.msmservice.service.MsmService;
import com.ssm.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/23 20:18
 */
@Api(description = "短信服务")
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "根据手机号发送短信")
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }

        code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);

       if (isSend) {
           redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
           return R.ok();
       } else {
           return R.error().message("短信发送失败");
       }

    }
}
