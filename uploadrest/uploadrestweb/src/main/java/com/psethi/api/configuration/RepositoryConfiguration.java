package com.psethi.api.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@Configuration
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EnableTransactionManagement
@ComponentScan({ "com.psethi.api.controller", "com.psethi.api.service", "com.psethi.api.entity.mapper" })
@EntityScan(basePackages = { "com.psethi.api.entity.model" })
@EnableJpaRepositories(basePackages = { "com.psethi.api.entity.repository" })
@PropertySource("classpath:application.properties")
public class RepositoryConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_DDL_AUTO = "hibernate.hbm2ddl.setting";
    private static final String PROPERTY_NAME_MULTIPART_MAXFILESIZE = "multipart.maxFileSize";

    @Resource
    private Environment env;

    /**
     * Data Source Set up
     * 
     **/
    @Bean
    public DataSource dataSource() {
        logger.info("*********Begin dataSource()***********");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        logger.info("*********End dataSource()***********");
        return dataSource;
    }

    /**
     * Entity Manager Set up
     * 
     **/
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        logger.info("*********Begin entityManagerFactory()***********");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        logger.info("*********End entityManagerFactory()***********");
        return entityManagerFactoryBean;
    }

    /**
     * Hibernate properties Set up
     * 
     **/
    private Properties hibProperties() {
        logger.info("*********Begin hibProperties()***********");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put("hibernate.show_sql", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DDL_AUTO));
        logger.info("*********Begin hibProperties()***********");
        return properties;
    }

    /**
     * Transaction Manager Set up
     * 
     **/
    @Bean
    public JpaTransactionManager transactionManager() {
        logger.info("*********Begin transactionManager()***********");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        logger.info("*********End transactionManager()***********");
        return transactionManager;
    }

    /**
     * Model Mapper Set up
     * 
     **/
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Multipart Config Set up
     * 
     **/
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        logger.info("*********Begin multipartResolver()***********");
        return new CommonsMultipartResolver();
    }

    /**
     * Multipart Resolver Set up
     * 
     **/
    @Bean
    public MultipartFilter multipartFilter() {
        logger.info("*********Begin multipartFilter()***********");
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

}
