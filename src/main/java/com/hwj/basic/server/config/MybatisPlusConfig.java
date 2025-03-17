package com.hwj.basic.server.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.hwj.basic.server.support.mybatis.sqlhandler.MyBatisPlusMetaObjectHandler;
import com.hwj.basic.server.support.mybatis.sqlinjector.EasySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: hwj
 * @Description:
 * @author: peng.huang
 * @since: 2025-03-17 14:43:30
 */
@Configuration
public class MybatisPlusConfig {


    /**
     * mybatis-plus插件
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    @Bean
    public ISqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }

    @Bean
    public MetaObjectHandler myBatisPlusMetaObjectHandler() {
        return new MyBatisPlusMetaObjectHandler();
    }
}
