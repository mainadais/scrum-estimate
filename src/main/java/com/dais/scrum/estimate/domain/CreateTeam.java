package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Team;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
import java.util.function.Supplier;

@Data
public class CreateTeam implements Supplier<Team> {

    private String name;
    private String organization;
    private UUID organizer;
    private Date dateCreated;

    @Override
    public Team get() {
        return Team.builder()
                .name(getName())
                .organization(getOrganization())
                .organizer(getOrganizer())
                .dateCreated(new Date())
                .build();
    }
}
