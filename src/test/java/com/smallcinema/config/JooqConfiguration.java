package com.smallcinema.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DSLContext jooqContext(DataSource dataSource) {
        return new DefaultDSLContext(dataSource, SQLDialect.POSTGRES);
    }
}
