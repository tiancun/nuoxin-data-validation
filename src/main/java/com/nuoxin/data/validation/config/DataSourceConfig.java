package com.nuoxin.data.validation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "eappDS")
    @Qualifier("eappDS")
    @Primary
    @ConfigurationProperties(prefix="spring.eapp.datasource")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mainDS")
    @Qualifier("mainDS")
    @ConfigurationProperties(prefix="spring.main.datasource")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }
}
