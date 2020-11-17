package com.dais.scrum.estimate.security.config;

import com.dais.scrum.estimate.security.JwtSecurityProperties;
import com.dais.scrum.estimate.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

@Configuration
@PropertySource("classpath:application-test.properties")
public class JwtSecurityTestConfig {

    @Value("${jwt.passwordStrength}")
    int passwordStrength;
    @Value("${jwt.tokenIssuer}")
    String tokenIssuer;
    @Value("${jwt.tokenSecret}")
    String tokenSecret;

    @MockBean
    JwtUserDetailsService jwtUserDetailsService;

    @Bean
    public JwtSecurityProperties jwtSecurityProperties() {
        return new JwtSecurityProperties(passwordStrength, tokenIssuer, tokenSecret, Duration.ofHours(4));
    }
}
