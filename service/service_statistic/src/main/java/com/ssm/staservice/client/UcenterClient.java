package com.ssm.staservice.client;

import com.ssm.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/17 14:02
 */
@FeignClient(name="service-ucenter",fallback = UcenterDegradeFeginClient.class)
@Component
public interface UcenterClient {

    @PostMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
