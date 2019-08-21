package com.ayou.live.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * description
 *
 * @author ysy
 * @date 2019-08-19
 */
@Configuration
public class LiveDataSourceConfig {
    @Primary
    @Bean(name = "liveDataSource")
    @Qualifier("liveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.live")
    public DataSource liveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userDataSource")
    @Qualifier("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "liveJdbcTemplate")
    public JdbcTemplate liveJdbcTemplate(@Qualifier("liveDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "userJdbcTemplate")
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
