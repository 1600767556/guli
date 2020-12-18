package com.ssm.eduorder.client;

import com.ssm.commonutils.vo.CourseWebVoOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/12/15 21:12
 */
@FeignClient(name="service-edu",fallback = EduDegradeFeginClient.class)
@Component
public interface EduClient {


    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
