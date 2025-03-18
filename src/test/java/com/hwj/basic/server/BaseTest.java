package com.hwj.basic.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 16:17:41
 */
@SpringBootTest(classes = Application.class)
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
