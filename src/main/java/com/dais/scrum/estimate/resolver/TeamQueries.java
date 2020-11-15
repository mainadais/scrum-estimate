package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.service.TeamService;

import java.util.UUID;

public interface TeamQueries {

    TeamService getTeamService();

    default Result<Team> findTeamById(UUID teamId) {
        return getTeamService().findById(teamId);
    }

    default Result<Team> findByOrganizer(UUID organizerId, String teamName) {
        return getTeamService().findByOrganizer(organizerId, teamName);
    }
}
