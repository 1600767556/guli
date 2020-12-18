package com.ssm.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.commonutils.R;
import com.ssm.eduservice.entity.EduCourse;
import com.ssm.eduservice.entity.EduTeacher;
import com.ssm.eduservice.service.EduCourseService;
import com.ssm.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/12 10:35
 */
@Api(description = "前台讲师管理")
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "分页查询讲师")
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit) {
        Page<EduTeacher> pageParam = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageParam);
        //返回分页所有数据
        return R.ok().data(map);

    }

    @ApiOperation(value = "讲师详情")
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        QueryWrapper wrapper =  new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);

    }
}
