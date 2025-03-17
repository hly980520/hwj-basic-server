package com.hwj.basic.server.config;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 19:46:21
 */
@Configuration
@EnableNacosConfig
@NacosPropertySource(dataId = "hwj-basic-server-env.properties", groupId = "hwj", autoRefreshed = true)
public class NacosConfig {
}
