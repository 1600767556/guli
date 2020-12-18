package com.ssm.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/17 10:39
 */
@Component
public class OrdersClientDegradeFeignClient implements OrdersClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
