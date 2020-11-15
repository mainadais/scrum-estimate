package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.repository.TeamRepository;
import com.dais.scrum.estimate.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Result<Team> findByOrganizer(UUID organizerId, String teamName) {
        try {
            return new Some<>(teamRepository.findByOrganizer(organizerId, teamName));
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
}
