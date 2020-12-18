package com.ssm.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssm.eduservice.entity.EduCourse;
import com.ssm.eduservice.entity.frontvo.CourseWebVo;
import com.ssm.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-11-09
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
