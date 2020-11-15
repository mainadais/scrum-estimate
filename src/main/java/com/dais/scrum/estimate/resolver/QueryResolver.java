package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.service.PlayerService;
import com.dais.scrum.estimate.service.TeamService;
import com.dais.scrum.estimate.service.VoteService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver, TeamQueries, PlayerQueries, VoteQueries {

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
