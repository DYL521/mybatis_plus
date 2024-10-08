package com.dyl.mybatis_plus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段填充策略
 */
@Slf4j
@Component //一定要加入到IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
    }

    // 更新时填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill");
        metaObject.setValue("updateTime", new Date());
    }
}
