package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Player;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class UpdatePassword implements Function<Player, Player> {

    private String oldPassword;
    private String newPassword;
    private UUID player;

    @Override
    public Player apply(Player player) {
        player.getAccount().setPassword(getNewPassword());
        return player;
    }
}
