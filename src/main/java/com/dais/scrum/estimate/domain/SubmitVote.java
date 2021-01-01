package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Participant;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.entity.Vote;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Data
public class SubmitVote implements Function<Team, Team> {

    private UUID participant;
    private UUID team;
    private String vote;

    @Override
    public Team apply(Team team) {
        Optional<Participant> participant = team.getParticipants().stream().filter(p -> p.getId().equals(getParticipant())).findFirst();
        participant.ifPresent(value -> value.setVote(getVote()));
        return team;
    }
}
