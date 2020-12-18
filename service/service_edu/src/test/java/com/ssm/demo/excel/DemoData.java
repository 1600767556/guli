package com.ssm.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 14:57
 */
@Data
public class DemoData {
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
}
