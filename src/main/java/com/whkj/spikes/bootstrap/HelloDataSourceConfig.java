package com.whkj.spikes.bootstrap;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 *
 * @author ksc
 */
@Configuration
@PropertySource({"classpath:application.properties", "classpath:conf.properties"})
@MapperScan(basePackages = "com.whkj.spikes.dao", sqlSessionFactoryRef = "helloSqlSessionFactory")
public class HelloDataSourceConfig {

    @Primary
    @Bean(name = "helloDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.hello")
    public DataSource helloDataSource() {
      return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "helloSqlSessionFactory")
    public SqlSessionFactory helloSqlSessionFactory(@Qualifier("helloDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver prpr = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(prpr.getResources("classpath:mybatis/**/*Mapper.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.whkj.spikes.dao.dataobject");
        sessionFactoryBean.setConfigLocation(prpr.getResource("classpath:mybatis-config.xml"));
        return sessionFactoryBean.getObject();
    }

}

