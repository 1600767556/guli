package com.ssm.eduservice.entity.vo;

import lombok.Data;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/14 21:12
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
