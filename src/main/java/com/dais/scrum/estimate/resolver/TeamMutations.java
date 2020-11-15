package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.service.TeamService;

import java.util.UUID;

public interface TeamMutations {

    TeamService getTeamService();

    default Result<Team> createTeam(CreateTeam createTeam) {
        return getTeamService().createTeam(createTeam);
    }

    default Result<Team> updateTeam(UpdateTeam updateTeam) {
        return getTeamService().updateTeam(updateTeam);
    }

    default Result<Team> addParticipant(AddParticipant addParticipant) {
        return getTeamService().addParticipant(addParticipant);
    }

    default Result<Team> dropParticipant(DropParticipant dropParticipant) {
        return getTeamService().dropParticipant(dropParticipant);
    }

    default Result<Team> clearParticipants(UUID teamId) {
        return getTeamService().clearParticipants(teamId);
    }

    default Result<Integer> dropTeam(UUID teamId) {
        return getTeamService().dropTeam(teamId);
    }
}
