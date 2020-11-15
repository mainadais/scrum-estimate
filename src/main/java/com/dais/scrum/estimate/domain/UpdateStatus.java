package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Account;
import com.dais.scrum.estimate.entity.Player;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class UpdateStatus implements Function<Player, Player> {

    private Account.Status status;
    private UUID player;

    @Override
    public Player apply(Player player) {
        player.getAccount().setStatus(getStatus());
        return player;
    }
}
