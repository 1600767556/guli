package com.ssm.msmservice.service;

import java.util.Map;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/23 20:19
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
