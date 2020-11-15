package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.service.PlayerService;
import com.dais.scrum.estimate.service.TeamService;
import com.dais.scrum.estimate.service.VoteService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver, TeamMutations, PlayerMutations, VoteMutations {

    final PlayerService playerService;
    final TeamService teamService;
    final VoteService voteService;

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
}
