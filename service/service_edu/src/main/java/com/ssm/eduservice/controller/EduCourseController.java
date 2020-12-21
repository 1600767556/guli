package com.ssm.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.commonutils.R;
import com.ssm.eduservice.entity.EduCourse;
import com.ssm.eduservice.entity.vo.CourseInfoVo;
import com.ssm.eduservice.entity.vo.CoursePublishVo;
import com.ssm.eduservice.entity.vo.CourseQuery;
import com.ssm.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-09
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "增加课程!")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    @ApiOperation(value = "根据课程ID查询课程的基本信息!")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    @ApiOperation(value = "修改课程信息!")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    @ApiOperation(value = "根据id获取课程信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
    CoursePublishVo coursePublishVo =  courseService.publishCourseInfo(id);
    return R.ok().data("publishCourse",coursePublishVo);
    }

    @ApiOperation(value = "根据id修改章节信息")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation(value = "获取所有课程")
    @GetMapping
    public R getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "分页查询Course")
    @GetMapping("pageCourse/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页数",required = true)
                             @PathVariable long current,
                             @ApiParam(name = "limit",value = "数据条目",required = true)
                             @PathVariable long limit) {
        Page<EduCourse> page = new Page<>(current,limit);
        courseService.page(page,null);
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "多条件组合查询")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(   @ApiParam(name = "current",value = "当前页数",required = true)
                                    @PathVariable long current,
                                    @ApiParam(name = "limit",value = "数据条目",required = true)
                                    @PathVariable long limit,
                                    @RequestBody(required = false)
                                    @ApiParam(name = "courseQuery",value = "课程查询",required = true)
                                            CourseQuery courseQuery) {
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_modified");
        courseService.page(pageCourse,wrapper);
        List<EduCourse> records = pageCourse.getRecords();
        long total = pageCourse.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


    @ApiOperation(value = "根据id删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }
 }

