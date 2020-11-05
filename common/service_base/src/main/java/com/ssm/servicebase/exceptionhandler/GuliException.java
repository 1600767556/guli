package com.ssm.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/4 19:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException {
    //状态码
    private Integer code;
    //异常信息
    private String msg;
}
