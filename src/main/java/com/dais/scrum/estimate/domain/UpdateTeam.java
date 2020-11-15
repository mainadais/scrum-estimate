package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Team;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class UpdateTeam implements Function<Team, Team> {

    private String name;
    private String organization;
    private UUID team;

    @Override
    public Team apply(Team team) {
        team.setName(getName());
        team.setOrganization(getOrganization());
        return team;
    }
}
