package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Team;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
import java.util.function.Supplier;

@Data
public class CreateTeam implements Supplier<Team> {

    private String name;
    private UUID organizer;
    private String[] choices;
    private Date dateCreated;

    @Override
    public Team get() {
        return Team.builder()
                .name(getName())
                .organizer(getOrganizer())
                .choices(getChoices())
                .dateCreated(new Date())
                .build();
    }
}
