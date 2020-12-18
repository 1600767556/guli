package com.ssm.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssm.eduservice.entity.EduCourse;
import com.ssm.eduservice.entity.frontvo.CourseFrontVo;
import com.ssm.eduservice.entity.frontvo.CourseWebVo;
import com.ssm.eduservice.entity.vo.CourseInfoVo;
import com.ssm.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-09
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo);


    CourseWebVo getbaseCourseInfo(String courseId);

}
