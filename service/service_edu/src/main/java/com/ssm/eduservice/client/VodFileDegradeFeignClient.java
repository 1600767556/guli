package com.ssm.eduservice.client;

import com.ssm.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/21 15:47
 */
@Component
public class VodFileDegradeFeignClient implements  VodClient {
    @Override
    public R removeAliyunVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }


}
