package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Player;
import lombok.Data;

import java.util.Date;
import java.util.function.Supplier;

@Data
public class CreatePlayer implements Supplier<Player> {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date dateCreated;

    @Override
    public Player get() {
        return Player.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .emailAddress(getEmailAddress())
                .role(Player.Role.GUEST)
                .dateCreated(new Date())
                .build();
    }
}
