package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Team;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class DropParticipant implements Function<Team, Team> {

    private UUID player;
    private UUID team;

    @Override
    public Team apply(Team team) {
        team.getParticipants().removeIf(participant ->
                participant.getPlayer().equals(getPlayer()) &&
                        participant.getTeam().equals(getTeam()));
        return team;
    }
}
