package com.hwj.basic.server.support.mybatis.sqlhandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @Program: hwj
 * @Description:
 * @author: peng.huang
 * @since: 2025-03-17 14:50:00
 */
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // fieldName 指的是实体类的属性名,而不是数据库的字段名
        strictInsertFill(metaObject, "deleted", Boolean.class, Boolean.FALSE);
        strictInsertFill(metaObject, "createdDate", LocalDateTime.class, LocalDateTime.now());
        strictInsertFill(metaObject, "updatedDate", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updatedDate", LocalDateTime.class, LocalDateTime.now());
    }

}
