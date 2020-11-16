package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.service.PlayerService;

import java.util.UUID;

public interface PlayerMutations {

    PlayerService getPlayerService();

    default Result<Player> addAccount(AddAccount addAccount) {
        return getPlayerService().addAccount(addAccount);
    }

    default Result<Player> updatePassword(UpdatePassword updatePassword) {
        return getPlayerService().updatePassword(updatePassword);
    }

    default Result<Player> createPlayer(CreatePlayer createPlayer) {
        return getPlayerService().createPlayer(createPlayer);
    }

    default Result<Player> updatePlayer(UpdatePlayer updatePlayer) {
        return getPlayerService().updatePlayer(updatePlayer);
    }

    default Result<Player> updateStatus(UpdateStatus updateStatus) {
        return getPlayerService().updateStatus(updateStatus);
    }

    default Result<Player> updateRole(UpdateRole updateRole) {
        return getPlayerService().updateRole(updateRole);
    }

    default Result<Integer> deletePlayer(UUID playerId) {
        return getPlayerService().deletePlayer(playerId);
    }
}
