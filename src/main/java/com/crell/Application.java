package com.crell;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.crell.core.interceptor.PageInterceptor;
import com.crell.core.util.LogUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.crell.common.mapper")
public class Application {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        sqlSessionFactoryBean.setTypeAliasesPackage("com.crell.common.model,com.crell.core.dto");

        Interceptor[] interceptors = {new PageInterceptor()};
        sqlSessionFactoryBean.setPlugins(interceptors);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public HttpMessageConverter httpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        return fastJsonHttpMessageConverter;
    }


    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LogUtil.info("SpringBoot Start Success");
    }

}
