package com.zht.iclass.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.zht.iclass.dao",sqlSessionFactoryRef = "iclassSqlSessionFactory")
public class DataSourceConfig {

    @Bean("iclassDataSource")
    @ConfigurationProperties("datasource")
    public DataSource iclassDataSource(){ return DruidDataSourceBuilder.create().build(); }

    @Bean(name = "iclassTransactionManager")
    public DataSourceTransactionManager bloomTransactionManager(@Qualifier("iclassDataSource") DataSource iclassDataSource) {
        return new DataSourceTransactionManager(iclassDataSource);
    }

    @Bean(name = "iclassSqlSessionFactory")
    public SqlSessionFactory bloomSqlSessionFactory(@Qualifier("iclassDataSource") DataSource iclassDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(iclassDataSource);
        return sessionFactory.getObject();
    }
}
