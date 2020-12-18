package com.ssm.eduservice.client;

import com.ssm.commonutils.vo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/15 9:57
 */
@FeignClient(name="service-ucenter",fallback = UcenterDegradeFeginClient.class)
@Component
public interface UcenterClient {

    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);

}

