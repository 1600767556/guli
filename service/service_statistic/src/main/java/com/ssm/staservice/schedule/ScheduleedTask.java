package com.ssm.staservice.schedule;

import com.ssm.staservice.service.StatisticsDailyService;
import com.ssm.staservice.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/17 15:40
 */
@Component
public class ScheduleedTask {

    @Autowired
    private StatisticsDailyService staService;

    @ApiOperation(value = "每天凌晨一点,查询前一天数据进行添加")
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
