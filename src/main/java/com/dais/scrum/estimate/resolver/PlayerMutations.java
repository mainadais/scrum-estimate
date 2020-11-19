package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.service.PlayerService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface PlayerMutations {

    PlayerService getPlayerService();

    @PreAuthorize("isAnonymous()")
    default Result<Player> addAccount(AddAccount addAccount) {
        return getPlayerService().addAccount(addAccount);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> updatePassword(UpdatePassword updatePassword) {
        return getPlayerService().updatePassword(updatePassword);
    }

    @PreAuthorize("isAnonymous()")
    default Result<Player> createPlayer(CreatePlayer createPlayer) {
        return getPlayerService().createPlayer(createPlayer);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> updatePlayer(UpdatePlayer updatePlayer) {
        return getPlayerService().updatePlayer(updatePlayer);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    default Result<Player> updateStatus(UpdateStatus updateStatus) {
        return getPlayerService().updateStatus(updateStatus);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    default Result<Player> updateRole(UpdateRole updateRole) {
        return getPlayerService().updateRole(updateRole);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    default Result<Integer> deletePlayer(UUID playerId) {
        return getPlayerService().deletePlayer(playerId);
    }
}
