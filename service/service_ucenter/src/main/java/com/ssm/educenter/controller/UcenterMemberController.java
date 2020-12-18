package com.ssm.educenter.controller;


import com.ssm.commonutils.JwtUtils;
import com.ssm.commonutils.R;
import com.ssm.commonutils.vo.UcenterMemberOrder;
import com.ssm.educenter.entity.UcenterMember;
import com.ssm.educenter.entity.vo.RegisterVo;
import com.ssm.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-24
 */
@Api(description = "用户登录服务")
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "登录服务")
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {

        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "注册服务")
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);
    }

    @ApiOperation(value = "根据用户id获取用户信息 评论微信支付使用")
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id) {
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    @ApiOperation(value = "查询某一天的注册人数")
    @PostMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }


}

