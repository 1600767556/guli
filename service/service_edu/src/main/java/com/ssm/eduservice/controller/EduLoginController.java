package com.ssm.eduservice.controller;

import com.ssm.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/7 10:08
 */
@Api(description = "管理员登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @ApiOperation(value = "管理员用户名密码")
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "管理员信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
