package com.ssm.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.google.gson.Gson;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/21 12:23
 */
public class getVideoAdderss {
    public static void main(String[] args) {
        // 创建SubmitMediaInfoJob实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-Shanghai",                // // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                "LTAI4GAy3kKs61KdpLm61h6a",        // 您的AccessKey ID
                "HVVPGMswx8X8fWo4lg07NID4VNH1ND" );    // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // 视频ID。
        request.setVideoId("db8eb61222d84d07872234ef6d0f6444");
        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            for (GetPlayInfoResponse.PlayInfo playInfo : response.getPlayInfoList()) {
                // 播放地址
                System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}
