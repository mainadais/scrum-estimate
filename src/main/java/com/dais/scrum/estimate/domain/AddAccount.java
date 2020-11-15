package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Account;
import com.dais.scrum.estimate.entity.Player;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
public class AddAccount implements Function<Player, Player> {

    private String username;
    private String password;
    private UUID player;

    @Override
    public Player apply(Player player) {
        player.setAccount(Account.builder()
                .username(getUsername())
                .password(getPassword())
                .status(Account.Status.pending_confirmation)
                .build());
        return player;
    }
}
