package com.ssm.staservice.controller;


import com.ssm.commonutils.R;
import com.ssm.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author shaoshao
 * @since 2020-12-17
 */
@Api(description = "数据展示服务")
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    @ApiOperation(value = "查询某天的注册人数")
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return R.ok();
    }
    @ApiOperation(value = "展示数据")
    @GetMapping("showData/{type}/{begin}/{end}")
    public R registerCount(@PathVariable String type,@PathVariable String begin,@PathVariable String end) {
        Map<String,Object> map = staService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}

