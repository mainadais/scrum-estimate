package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.ListResult;
import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.service.TeamService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface TeamQueries extends ResultHandler {

    TeamService getTeamService();

    @PreAuthorize("isAuthenticated()")
    default Result<Team> findTeamById(UUID teamId) {
        return sendResponse(getTeamService().findById(teamId));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> findTeamByName(UUID organizerId, String teamName) {
        return sendResponse(getTeamService().findTeamByName(organizerId, teamName));
    }

    @PreAuthorize("isAuthenticated()")
    default ListResult<Team> findTeamsByOrganizer(UUID organizerId) {
        return sendResponse(new ListResult<>(getTeamService().findTeamsByOrganizer(organizerId)));
    }

    @PreAuthorize("isAuthenticated()")
    default ListResult<Team> findTeamsJoined(UUID playerId) {
        return sendResponse(new ListResult<>(getTeamService().findTeamsJoined(playerId)));
    }
}
