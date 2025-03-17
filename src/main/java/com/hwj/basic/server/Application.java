package com.hwj.basic.server;

import com.hwj.dubbo.compatible.config.spring.EnableHwjDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Program: hwj-user-center
 * @Description: 应用启动类
 * @author: wenjing.huang
 * @since: 2025-03-14 14:13:39
 */
@SpringBootApplication(
        exclude = {
                JmsAutoConfiguration.class,
                ActiveMQAutoConfiguration.class,
                KafkaAutoConfiguration.class,
                QuartzAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                SessionAutoConfiguration.class
        }
)
//@EnableHwjDubbo
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
