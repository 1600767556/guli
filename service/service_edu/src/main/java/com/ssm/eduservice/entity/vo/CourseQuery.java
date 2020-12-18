package com.ssm.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/15 15:14
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;
    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
