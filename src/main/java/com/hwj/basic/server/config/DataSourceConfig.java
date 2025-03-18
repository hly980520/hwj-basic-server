package com.hwj.basic.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 14:02:37
 */
@Configuration
@ConfigurationProperties(prefix = "hwj.data-source.druid")
public class DataSourceConfig {
    /**
     * 驱动
     */
    private String driverClassName;
    /**
     * 数据库连接
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 初始化连接数
     */
    private Integer initialSize;
    /**
     * 连接存活时间
     */
    private Integer maxActive;
    /**
     * 最小连接数
     */
    private Integer minIdle;
    /**
     * 连接获取等待时间
     */
    private Integer maxWait;

    private Boolean keepAlive;

    private Boolean removerAbandoned;

    private Integer removerAbandonedTimeout;

    private Boolean testOnBorrow;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMaxActive(this.maxActive);
        //此配置开启可保证连接池中的可用连接数保持在minIdle数量以内
        dataSource.setKeepAlive(this.keepAlive);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setMaxWait(this.maxWait);
        dataSource.setRemoveAbandoned(this.removerAbandoned);
        dataSource.setRemoveAbandonedTimeout(this.removerAbandonedTimeout);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.addFilters("mergeStat");
        return dataSource;
    }


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRemoverAbandoned() {
        return removerAbandoned;
    }

    public void setRemoverAbandoned(Boolean removerAbandoned) {
        this.removerAbandoned = removerAbandoned;
    }

    public Integer getRemoverAbandonedTimeout() {
        return removerAbandonedTimeout;
    }

    public void setRemoverAbandonedTimeout(Integer removerAbandonedTimeout) {
        this.removerAbandonedTimeout = removerAbandonedTimeout;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
}
