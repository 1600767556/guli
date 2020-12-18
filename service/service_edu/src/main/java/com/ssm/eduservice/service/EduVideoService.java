package com.ssm.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssm.eduservice.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-09
 */
public interface EduVideoService extends IService<EduVideo> {

    //根据id删除小节
    void removeVideoByCourseId(String courseId);


}
