package com.dais.scrum.estimate.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dais.scrum.estimate.domain.CreatePlayer;
import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.domain.UpdatePassword;
import com.dais.scrum.estimate.domain.UpdateRole;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.security.exception.BadCredentialsException;
import com.dais.scrum.estimate.security.exception.BadTokenException;
import com.dais.scrum.estimate.security.exception.UserExistsException;
import com.dais.scrum.estimate.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private static final String ADMIN_AUTHORITY = "ADMIN";
    private static final String USER_AUTHORITY = "USER";
    private static final String PLAYER_AUTHORITY = "PLAYER";
    private static final String GUEST_AUTHORITY = "GUEST";

    private final PlayerService playerService;
    private final JwtSecurityProperties properties;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final PasswordEncoder passwordEncoder;

    private Optional<Player> findById(UUID playerId) {
        Result<Player> player = playerService.findById(playerId);
        return player.getData() != null ? Optional.of(player.getData()) : Optional.empty();
    }

    private Optional<Player> findByEmail(String email) {
        Result<Player> player = playerService.findByEmail(email);
        return player.getData() != null ? Optional.of(player.getData()) : Optional.empty();
    }

    private Boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    private Boolean existsById(UUID playerId) {
        return findById(playerId).isPresent();
    }

    private void deleteById(UUID playerId) {
        playerService.deletePlayer(playerId);
    }

    @Override
    @Transactional
    public JwtUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email)
                .map(user -> getUserDetails(user, getToken(user)))
                .orElseThrow(() -> new UsernameNotFoundException("Username or password did not match"));
    }

    @Transactional
    public JwtUserDetails loadUserByToken(String token) {
        return getDecodedToken(token)
                .map(DecodedJWT::getSubject)
                .flatMap(this::findByEmail)
                .map(user -> getUserDetails(user, token))
                .orElseThrow(BadTokenException::new);
    }

    @Transactional
    public Player getCurrentUser() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(this::findByEmail)
                .orElse(null);
    }

    @Transactional
    public Player createUser(CreatePlayer createPlayer) {
        if (!exists(createPlayer)) {
            return playerService.createPlayer(createPlayer).getData();
        } else {
            throw new UserExistsException(createPlayer.getEmailAddress());
        }
    }

    @Transactional
    public Player updatePassword(UpdatePassword updatePassword) {
        Optional<Player> playerResult = findById(updatePassword.getPlayer());
        if (playerResult.isPresent()) {
            Player player = playerResult.get();
            if (passwordEncoder.matches(updatePassword.getOldPassword(), player.getAccount().getPassword())) {
                Result<Player> result = playerService.updatePassword(updatePassword);
                return result.getData();
            } else {
                throw new BadCredentialsException(player.getEmailAddress());
            }
        }
        throw new UserExistsException("User does not exist");
    }

    @Transactional
    public String getToken(Player user) {
        Instant now = Instant.now();
        Instant expiry = Instant.now().plus(properties.getTokenExpiration());
        return JWT
                .create()
                .withIssuer(properties.getTokenIssuer())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .withSubject(user.getEmailAddress())
                .sign(algorithm);
    }

    public boolean isAdmin() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .stream()
                .flatMap(Collection::stream)
                .map(GrantedAuthority::getAuthority)
                .anyMatch(ADMIN_AUTHORITY::equals);
    }

    public boolean isAuthenticated() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(not(this::isAnonymous))
                .isPresent();
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken;
    }

    @Transactional
    public boolean deleteUser(String player) {
        UUID playerId = UUID.fromString(player);
        if (existsById(playerId)) {
            deleteById(playerId);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Player assignAdmin(String player) {
        UpdateRole updateRole = new UpdateRole();
        updateRole.setRole(Player.Role.ADMIN);
        updateRole.setPlayer(UUID.fromString(player));
        return playerService.updateRole(updateRole).getData();
    }

    @Transactional
    public Player revokeAdmin(String player) {
        UpdateRole updateRole = new UpdateRole();
        updateRole.setRole(Player.Role.USER);
        updateRole.setPlayer(UUID.fromString(player));
        return playerService.updateRole(updateRole).getData();
    }

    private boolean exists(CreatePlayer createPlayer) {
        return existsByEmail(createPlayer.getEmailAddress());
    }

    private JwtUserDetails getUserDetails(Player user, String token) {
        return JwtUserDetails
                .builder()
                .username(user.getEmailAddress())
                .password(user.getAccount().getPassword())
                .role(user.getRole())
                .status(user.getAccount().getStatus())
                .role(user.getRole())
                .token(token)
                .build();
    }

    private Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
