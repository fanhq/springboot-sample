package com.fanhq.example.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
@Data
@ConfigurationProperties(prefix = "database0")
@Component
public class Database0Config {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String databaseName;

    public DataSource createDataSource() {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(getDriverClassName());
        result.setJdbcUrl(getUrl());
        result.setUsername(getUsername());
        result.setPassword(getPassword());
        return result;
    }
}

