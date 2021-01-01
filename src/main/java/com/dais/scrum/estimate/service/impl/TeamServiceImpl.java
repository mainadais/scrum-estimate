package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Participant;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.repository.TeamRepository;
import com.dais.scrum.estimate.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    final TeamRepository teamRepository;

    @Override
    public Result<Team> findById(UUID teamId) {
        try {
            Optional<Team> find = teamRepository.findById(teamId);
            if (find.isPresent()) {
                return new Some<>(find.get());
            }
            return new None<>("No team found with provided team id");
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> findTeamByName(UUID organizerId, String teamName) {
        try {
            return new Some<>(teamRepository.findTeamByName(organizerId, teamName));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<List<Team>> findTeamsByOrganizer(UUID organizerId) {
        try {
            return new Some<>(teamRepository.findTeamsByOrganizer(organizerId));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<List<Team>> findTeamsJoined(UUID playerId) {
        try {
            return new Some<>(teamRepository.findTeamsJoined(playerId));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> createTeam(CreateTeam createTeam) {
        try {
            return new Some<>(teamRepository.save(createTeam.get()));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> updateTeam(UpdateTeam updateTeam) {
        try {
            Result<Team> team = findById(updateTeam.getTeam());
            if (team.getData() != null) {
                return new Some<>(teamRepository.save(updateTeam.apply(team.getData())));
            }
            return team;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> addParticipant(AddParticipant addParticipant) {
        try {
            Result<Team> team = findById(addParticipant.getTeam());
            if (team.getData() != null) {
                return new Some<>(teamRepository.save(addParticipant.apply(team.getData())));
            }
            return team;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> dropParticipant(DropParticipant dropParticipant) {
        try {
            Result<Team> team = findById(dropParticipant.getTeam());
            if (team.getData() != null) {
                return new Some<>(teamRepository.save(dropParticipant.apply(team.getData())));
            }
            return team;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> clearParticipants(UUID teamId) {
        try {
            Result<Team> team = findById(teamId);
            if (team.getData() != null) {
                team.getData().getParticipants().clear();
                return new Some<>(teamRepository.save(team.getData()));
            }
            return team;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Integer> dropTeam(UUID teamId) {
        try {
            teamRepository.deleteById(teamId);
            return new Some<>(1);
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> joinTeam(UUID playerId, UUID teamId) {
        try {
            Optional<Team> teamById = teamRepository.findById(teamId);
            if (teamById.isPresent()) {
                Optional<Participant> existingParticipant = teamById.get().getParticipants().stream().filter(player -> player.getPlayer().equals(playerId)).findFirst();
                if (existingParticipant.isPresent()) {
                    return new Some<>(teamById.get());
                }
                //create new participant
                Participant newParticipant = Participant.builder()
                        .player(playerId)
                        .team(teamId)
                        .build();
                Team teamToJoin = teamById.get();
                teamToJoin.getParticipants().add(newParticipant);
                //update team with participant
                return new Some<>(teamRepository.save(teamToJoin));
            }
            return new None<>("No team exists with given team id");
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Team> submitVote(SubmitVote submitVote) {
        try {
            Result<Team> team = findById(submitVote.getTeam());
            if (team.getData() != null) {
                return new Some<>(teamRepository.save(submitVote.apply(team.getData())));
            }
            return team;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }
}
