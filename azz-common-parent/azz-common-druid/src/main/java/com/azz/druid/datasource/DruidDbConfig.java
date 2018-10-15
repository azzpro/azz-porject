package com.azz.druid.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月13日 上午11:08:06
 */
@Component
public class DruidDbConfig {
	
    private Logger logger = LoggerFactory.getLogger(DruidDbConfig.class);

    @Autowired
    private DruidConfig druidPoolConfig;

    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(druidPoolConfig.getUrl());
        datasource.setUsername(druidPoolConfig.getUsername());
        datasource.setPassword(druidPoolConfig.getPassword());
        datasource.setDriverClassName(druidPoolConfig.getDriverClassName());
        datasource.setInitialSize(druidPoolConfig.getInitialSize());
        datasource.setMinIdle(druidPoolConfig.getMinIdle());
        datasource.setMaxActive(druidPoolConfig.getMaxActive());
        datasource.setMaxWait(druidPoolConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidPoolConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidPoolConfig.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidPoolConfig.getValidationQuery());
        datasource.setTestWhileIdle(druidPoolConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(druidPoolConfig.isTestOnBorrow());
        datasource.setTestOnReturn(druidPoolConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidPoolConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidPoolConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(druidPoolConfig.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(druidPoolConfig.getConnectionProperties());
        return datasource;
    }
    
    @Configuration
    @EnableTransactionManagement
    @AutoConfigureAfter({DruidConfig.class})
    @MapperScan(basePackages = {"com.azz"})
    public class TransactionConfig implements  TransactionManagementConfigurer {
        @Autowired
        private DruidDataSource mDataSource;

        @Override
        public PlatformTransactionManager annotationDrivenTransactionManager() {
            return new DataSourceTransactionManager(mDataSource);
        }
    }

   
}
