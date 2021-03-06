package com.dais.scrum.estimate.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.dais.scrum.estimate.security.JwtSecurityProperties;
import com.dais.scrum.estimate.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfig {

    @Bean
    public Algorithm jwtAlgorithm(JwtSecurityProperties properties) {
        return Algorithm.HMAC256(properties.getTokenSecret());
    }

    @Bean
    public JWTVerifier verifier(JwtSecurityProperties properties, Algorithm algorithm) {
        return JWT
                .require(algorithm)
                .withIssuer(properties.getTokenIssuer())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(JwtSecurityProperties properties) {
        return new BCryptPasswordEncoder(properties.getPasswordStrength());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(JwtUserDetailsService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
