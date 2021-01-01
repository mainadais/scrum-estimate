package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.entity.Participant;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.repository.TeamRepository;
import com.dais.scrum.estimate.service.VoteService;
import com.dais.scrum.estimate.service.VotesPublisher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    final TeamRepository teamRepository;
    final VotesPublisher votesPublisher;

    @Override
    public Boolean publishVotes(UUID teamId) {
        try {
            Optional<Team> team = teamRepository.findById(teamId);
            if (team.isPresent()) {
                List<Participant> participants = team.get().getParticipants();
                votesPublisher.accept(participants.stream().map(p -> Vote.builder()
                        .vote(p.getVote())
                        .participant(p.getId())
                        .team(p.getTeam()).build()).collect(Collectors.toList()));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
