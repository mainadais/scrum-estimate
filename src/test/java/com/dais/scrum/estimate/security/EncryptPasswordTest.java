package com.dais.scrum.estimate.security;

import com.dais.scrum.estimate.security.config.JwtSecurityConfig;
import com.dais.scrum.estimate.security.config.JwtSecurityTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {JwtSecurityTestConfig.class, JwtSecurityConfig.class})
public class EncryptPasswordTest {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Test
    public void testEncodePassword() {
        String encrypted = encoder.encode("password");
        System.out.println(encrypted);
    }
}
