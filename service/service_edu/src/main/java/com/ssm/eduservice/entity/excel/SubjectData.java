package com.ssm.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 15:44
 */
@Data
public class SubjectData {
    @ExcelProperty(value = "课程一级名称",index = 0)
    private String  oneSubjectName;
    @ExcelProperty(value = "课程二级名称",index = 1)
    private String twoSubjectName;
}
