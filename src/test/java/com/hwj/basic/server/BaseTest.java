package com.hwj.basic.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Program: hwj
 * @Description:
 * @author: peng.huang
 * @since: 2025-03-17 16:17:41
 */
@SpringBootTest(value = "-Dnacos.server-addr=127.0.0.1:8848" +
        "-Dnacos.namespace=hwj_basic_business" +
        "-Dnacos.username=nacos" +
        "-Dnacos.password=nacos" +
        "-Dspring.profiles.active=dev" +
        "-Ddebug=true")
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
