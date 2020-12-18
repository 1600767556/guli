package com.ssm.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.ssm.oss.service.OssService;
import com.ssm.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 11:15
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uoloadFileAvatar(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //文件名：uuid.扩展名
            String fileName = file.getOriginalFilename();
            //在文件名称中添加随机胃一值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid+fileName;
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath+"/"+fileName;
            //文件上传至阿里云
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //获取url地址
            String url = "http://" + bucketName + "." + endPoint + "/" + fileName;
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
