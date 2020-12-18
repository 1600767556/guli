package com.ssm.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.commonutils.JwtUtils;
import com.ssm.commonutils.R;
import com.ssm.commonutils.vo.CourseWebVoOrder;
import com.ssm.eduservice.client.OrdersClient;
import com.ssm.eduservice.entity.EduCourse;
import com.ssm.eduservice.entity.chapter.ChapterVo;
import com.ssm.eduservice.entity.frontvo.CourseFrontVo;
import com.ssm.eduservice.entity.frontvo.CourseWebVo;
import com.ssm.eduservice.service.EduChapterService;
import com.ssm.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/12 10:35
 */
@Api(description = "前台课程管理")
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {


    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    @ApiOperation(value = "分页查询讲师")
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo CourseFrontVo) {
        Page<EduCourse> pageParam = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageParam,CourseFrontVo);
        return R.ok().data(map);
    }

    @ApiOperation(value = "课程详情")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return R.error().code(20001).message("请登录");
        }
        CourseWebVo courseWebVo = courseService.getbaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        boolean buyCourse = ordersClient.isBuyCourse(courseId, memberId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isbuyCourse",buyCourse);
    }

    @ApiOperation(value = "根据课程id查询课程信息")
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo= courseService.getbaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }

}
