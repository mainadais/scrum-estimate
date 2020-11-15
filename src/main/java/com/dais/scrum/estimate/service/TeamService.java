package com.dais.scrum.estimate.service;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Team;

import java.util.UUID;

public interface TeamService {

    Result<Team> findById(UUID teamId);

    Result<Team> findByOrganizer(UUID organizerId, String teamName);

    Result<Team> createTeam(CreateTeam createTeam);

    Result<Team> updateTeam(UpdateTeam updateTeam);

    Result<Team> addParticipant(AddParticipant addParticipant);

    Result<Team> dropParticipant(DropParticipant dropParticipant);

    Result<Team> clearParticipants(UUID teamId);

    Result<Integer> dropTeam(UUID teamId);
}
