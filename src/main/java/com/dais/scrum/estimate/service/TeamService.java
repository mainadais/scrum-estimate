package com.dais.scrum.estimate.service;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.entity.Vote;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    Result<Team> findById(UUID teamId);

    Result<Team> findTeamByName(UUID organizerId, String teamName);

    Result<List<Team>> findTeamsByOrganizer(UUID organizerId);

    Result<List<Team>> findTeamsJoined(UUID playerId);

    Result<Team> createTeam(CreateTeam createTeam);

    Result<Team> updateTeam(UpdateTeam updateTeam);

    Result<Team> addParticipant(AddParticipant addParticipant);

    Result<Team> dropParticipant(DropParticipant dropParticipant);

    Result<Team> clearParticipants(UUID teamId);

    Result<Integer> dropTeam(UUID teamId);

    Result<Team> joinTeam(UUID playerId, UUID teamId);

    Result<Team> submitVote(SubmitVote submitVote);
}
