package com.ssm.oss.controller;

import com.ssm.commonutils.R;
import com.ssm.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 11:15
 */
@Api(description = "上传文件")
@RestController
@RequestMapping("/eduoss/fileoss")
//@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "头像、轮播图上传")
    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        //返回上传到oss的路径
     String url = ossService.uoloadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
