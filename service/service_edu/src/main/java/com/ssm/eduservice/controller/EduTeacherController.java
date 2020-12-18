package com.ssm.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.commonutils.R;
import com.ssm.eduservice.entity.EduTeacher;
import com.ssm.eduservice.entity.vo.TeacherQuery;
import com.ssm.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-03
 */
@Api(description = "讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true)
                           @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        return (flag == true) ? R.ok() : R.error().message("删除失败");
    }

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "多条件组合查询")
    public R pageTeacherCondition(  @ApiParam(name = "current",value = "当前页数",required = true)
                                    @PathVariable long current,
                                    @ApiParam(name = "limit",value = "数据条目",required = true)
                                    @PathVariable long limit,
                                    @RequestBody(required = false)
                                    @ApiParam(name = "teacherQuery",value = "教师查询",required = true)
                                    TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(pageTeacher,wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 添加讲师的接口方法
     * @param teacher 教师
     * @return 结果
     */
    @ApiOperation("增加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = eduTeacherService.save(teacher);
        return (save == true) ? R.ok() : R.error();
    }


    /**
     * 根据id查询讲师
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduteacher",eduTeacher);

    }

    @ApiOperation(value = "根据ID修改讲师列表")
    @PostMapping("updateTeacherById/{id}")
    public R updateTeacher(@ApiParam(name = "id",value = "讲师ID",required = true)
                           @PathVariable String id) {
        EduTeacher teacher = new EduTeacher();
        teacher.setId(id);
        teacher.setName("ssm2");
        boolean flag = eduTeacherService.updateById(teacher);
        return (flag == true) ? R.ok() : R.error();
    }

    @ApiOperation(value = "修改讲师列表")
    @PostMapping("updateTeacher")
    public R updateTeacher(
            @ApiParam(name = "eduTeacher",value = "讲师",required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        return (flag == true) ? R.ok() : R.error();
    }
}

