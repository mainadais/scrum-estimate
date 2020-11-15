package com.dais.scrum.estimate;

import graphql.kickstart.spring.web.boot.GraphQLWebsocketAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class, GraphQLWebsocketAutoConfiguration.class})
class ScrumEstimateApplicationTests {

    @Test
    void contextLoads() {
    }

}
