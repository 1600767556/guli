package com.ssm.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/17 10:37
 */
@FeignClient(name = "service-order",fallback = OrdersClientDegradeFeignClient.class)
@Component
public interface OrdersClient {


    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
