package com.ssm.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/17 20:10
 */
public interface VodService {
    String uploadVideoAliyun(MultipartFile file);

    void removeMoreAliyunVideo(List videoIdList);

    String getAliyunVideoAddress(String id);
}
