package com.magfin.web.service;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Time 2019/4/9
 * @Author 佛祖保佑的最
 */
@Configuration
public class SqlSessionConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactoryBean = null;
        try{
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
        }catch (Exception e){
            logger.error("创建SqlSession连接工厂错误：{}", e);
        }
        return sqlSessionFactoryBean;
    }
}
