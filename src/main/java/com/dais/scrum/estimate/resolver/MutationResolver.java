package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.security.JwtUserDetails;
import com.dais.scrum.estimate.security.JwtUserDetailsService;
import com.dais.scrum.estimate.security.exception.BadCredentialsException;
import com.dais.scrum.estimate.service.PlayerService;
import com.dais.scrum.estimate.service.TeamService;
import com.dais.scrum.estimate.service.VoteService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver, TeamMutations, PlayerMutations, VoteMutations {

    final PlayerService playerService;
    final TeamService teamService;
    final VoteService voteService;

    final JwtUserDetailsService userDetailsService;
    final AuthenticationProvider authenticationProvider;

    @Override
    public TeamService getTeamService() {
        return this.teamService;
    }

    @Override
    public PlayerService getPlayerService() {
        return this.playerService;
    }

    @Override
    public VoteService getVoteService() {
        return this.voteService;
    }

    @PreAuthorize("isAnonymous()")
    public JwtUserDetails login(String emailAddress, String password) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(emailAddress, password);
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
            return ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(emailAddress);
        }
    }
}
