package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.service.PlayerService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface PlayerMutations extends ResultHandler {

    PlayerService getPlayerService();

    @PreAuthorize("isAnonymous()")
    default Result<Player> addAccount(AddAccount addAccount) {
        return sendResponse(getPlayerService().addAccount(addAccount));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> updatePassword(UpdatePassword updatePassword) {
        return sendResponse(getPlayerService().updatePassword(updatePassword));
    }

    @PreAuthorize("isAnonymous()")
    default Result<Player> createPlayer(CreatePlayer createPlayer) {
        return sendResponse(getPlayerService().createPlayer(createPlayer));
    }

    @PreAuthorize("isAnonymous()")
    default Result<Recovery> recoverLogin(RecoverLogin recoverLogin) {
        return sendResponse(getPlayerService().recoverLogin(recoverLogin));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> updatePlayer(UpdatePlayer updatePlayer) {
        return sendResponse(getPlayerService().updatePlayer(updatePlayer));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    default Result<Player> updateStatus(UpdateStatus updateStatus) {
        return sendResponse(getPlayerService().updateStatus(updateStatus));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    default Result<Player> updateRole(UpdateRole updateRole) {
        return sendResponse(getPlayerService().updateRole(updateRole));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    default Result<Integer> deletePlayer(UUID playerId) {
        return sendResponse(getPlayerService().deletePlayer(playerId));
    }
}
