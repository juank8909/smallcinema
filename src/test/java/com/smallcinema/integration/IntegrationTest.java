package com.smallcinema.integration;

import com.smallcinema.config.PGContainer;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {IntegrationTest.Initializer.class})
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WebAppConfiguration
public class IntegrationTest {

    @Inject
    private MockMvc mockMvc;

    @Container
    public static PGContainer postgreSQLContainer = PGContainer.getInstance();

    @Inject
    protected NamedParameterJdbcTemplate jdbcTemplate;

    static class Initializer implements ApplicationContextInitializer<GenericApplicationContext> {
        public void initialize(GenericApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();

            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUrl(postgreSQLContainer.getJdbcUrl());
            dataSource.setUser(postgreSQLContainer.getUsername());
            dataSource.setPassword(postgreSQLContainer.getPassword());

            DefaultDSLContext jooqContext = new DefaultDSLContext(dataSource, SQLDialect.POSTGRES);
            configurableApplicationContext.registerBean("dslContext", DSLContext.class, () -> jooqContext);
        }
    }

    @Test
    @DisplayName("Get a movie times show given a movieID")
    void test03() {
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/movie/{movieId}/times", "tt0232500")).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect();
            //.andExpect(jsonPath("$._internal_id").isEmpty()).andExpect(jsonPath("$.debts").exists());
        });

    }



}
