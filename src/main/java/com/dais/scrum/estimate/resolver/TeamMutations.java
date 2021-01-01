package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.TeamService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface TeamMutations extends ResultHandler {

    TeamService getTeamService();

    @PreAuthorize("isAuthenticated()")
    default Result<Team> createTeam(CreateTeam createTeam) {
        return sendResponse(getTeamService().createTeam(createTeam));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> updateTeam(UpdateTeam updateTeam) {
        return sendResponse(getTeamService().updateTeam(updateTeam));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> addParticipant(AddParticipant addParticipant) {
        return sendResponse(getTeamService().addParticipant(addParticipant));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> dropParticipant(DropParticipant dropParticipant) {
        return sendResponse(getTeamService().dropParticipant(dropParticipant));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> clearParticipants(UUID teamId) {
        return sendResponse(getTeamService().clearParticipants(teamId));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Integer> dropTeam(UUID teamId) {
        return sendResponse(getTeamService().dropTeam(teamId));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> joinTeam(UUID playerId, UUID teamId) {
        return sendResponse(getTeamService().joinTeam(playerId, teamId));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Team> submitVote(SubmitVote submitVote) {
        return sendResponse(getTeamService().submitVote(submitVote));
    }
}
