package com.jijo.module.config.datasources;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfiguration {

    /*
     * @Primary public DataSource getDataSource() { DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
     * dataSourceBuilder.driverClassName("org.h2.Driver");
     * dataSourceBuilder.url("jdbc:h2:mem:test;DB_CLOSE_ON_EXIT=FALSE"); dataSourceBuilder.username("SA");
     * dataSourceBuilder.password(""); return dataSourceBuilder.build(); }
     */

    @DependsOn({"dataSource"})
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        final JdbcTemplate jdbcTemplateDataExporter = new JdbcTemplate(dataSource);
        return jdbcTemplateDataExporter;
    }
}
