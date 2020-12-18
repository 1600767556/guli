package com.ssm.staservice.client;

import com.ssm.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/17 14:03
 */
@Component
public class UcenterDegradeFeginClient implements UcenterClient {
    @Override
    public R countRegister(String day) {
        return R.error().message("出错啦!");
    }
}
