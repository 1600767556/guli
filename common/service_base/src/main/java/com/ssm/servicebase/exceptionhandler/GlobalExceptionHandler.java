package com.ssm.servicebase.exceptionhandler;


import com.ssm.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/4 18:27
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 指定出现什么异常执行这个方法
     * @param e
     * @return
     * @ResponseBody 为了返回数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理...");
    }

    /**
     * 特定异常处理
     * @param e
     * @return
     * @ResponseBody 为了返回数据
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理...");
    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     * @ResponseBody 为了返回数据
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
