package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Player;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class UpdatePlayer implements Function<Player, Player> {

    private String firstName;
    private String lastName;
    private UUID player;

    @Override
    public Player apply(Player player) {
        player.setFirstName(getFirstName());
        player.setLastName(getLastName());
        return player;
    }
}
