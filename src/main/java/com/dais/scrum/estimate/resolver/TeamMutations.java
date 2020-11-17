package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.service.TeamService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface TeamMutations {

    TeamService getTeamService();

    @PreAuthorize("isAuthenticated()")
    default Result<Team> createTeam(CreateTeam createTeam) {
        return getTeamService().createTeam(createTeam);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> updateTeam(UpdateTeam updateTeam) {
        return getTeamService().updateTeam(updateTeam);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> addParticipant(AddParticipant addParticipant) {
        return getTeamService().addParticipant(addParticipant);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> dropParticipant(DropParticipant dropParticipant) {
        return getTeamService().dropParticipant(dropParticipant);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> clearParticipants(UUID teamId) {
        return getTeamService().clearParticipants(teamId);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Integer> dropTeam(UUID teamId) {
        return getTeamService().dropTeam(teamId);
    }
}
