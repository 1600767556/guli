package com.ssm.eduservice.controller;


import com.ssm.commonutils.R;
import com.ssm.eduservice.client.VodClient;
import com.ssm.eduservice.entity.EduVideo;
import com.ssm.eduservice.service.EduVideoService;
import com.ssm.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-09
 */
@Api(description = "视频管理")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation(value = "增加视频")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }
    @ApiOperation(value = "根据id删除视频")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {

        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId)) {
            R result =  vodClient.removeAliyunVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new GuliException(20001,"删除视频失败,熔断器");
            }
        }

        videoService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "修改视频")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return R.ok();
    }

    @ApiOperation(value = "根据id获取视频信息")
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(
            @PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("video", eduVideo);
    }




}

