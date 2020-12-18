package com.ssm.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 11:15
 */
public interface OssService {
    String uoloadFileAvatar(MultipartFile file);

}
