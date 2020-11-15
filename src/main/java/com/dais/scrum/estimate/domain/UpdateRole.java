package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Player;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class UpdateRole implements Function<Player, Player> {

    private Player.Role role;
    private UUID player;

    @Override
    public Player apply(Player player) {
        player.setRole(getRole());
        return player;
    }
}
