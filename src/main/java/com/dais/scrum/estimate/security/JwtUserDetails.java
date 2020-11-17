package com.dais.scrum.estimate.security;

import com.dais.scrum.estimate.entity.Account;
import com.dais.scrum.estimate.entity.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Builder
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetails {

    final String username;
    final String password;
    final Account.Status status;
    final Player.Role role;
    final String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status != Account.Status.expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != Account.Status.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status != Account.Status.expired;
    }

    @Override
    public boolean isEnabled() {
        return this.status != Account.Status.disabled;
    }
}
