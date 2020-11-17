package com.dais.scrum.estimate.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:jwt-security.properties")
@Configuration
public class JwtSecurityProperties {

    int passwordStrength;
    String tokenSecret;
    String tokenIssuer;
    Duration tokenExpiration = Duration.ofHours(4);
}
