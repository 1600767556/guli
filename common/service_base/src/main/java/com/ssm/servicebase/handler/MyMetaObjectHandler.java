package com.ssm.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/2 16:08
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     *    使用mp添加操作,这个方法执行
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);

    }
    /**
     *    使用mp修改操作,这个方法执行
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
