package com.ssm.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/9 9:43
 */
//二级分类
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
