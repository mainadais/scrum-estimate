package com.dais.scrum.estimate.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Getter
public class JwtPreAuthenticationToken extends PreAuthenticatedAuthenticationToken {

    @Builder
    public JwtPreAuthenticationToken(JwtUserDetails principal, WebAuthenticationDetails details) {
        super(principal, null, principal.getAuthorities());
        super.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
