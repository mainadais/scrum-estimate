package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.ListResult;
import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.service.PlayerService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface PlayerQueries extends ResultHandler {

    PlayerService getPlayerService();

    @PreAuthorize("isAuthenticated()")
    default Result<Player> findById(UUID playerId) {
        return sendResponse(getPlayerService().findById(playerId));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> findByEmail(String emailAddress) {
        return sendResponse(getPlayerService().findByEmail(emailAddress));
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> findByUsername(String username) {
        return sendResponse(getPlayerService().findByUsername(username));
    }

    @PreAuthorize("isAuthenticated()")
    default ListResult<Player> findPlayersByTeam(UUID teamId) {
        return sendResponse(new ListResult<>(getPlayerService().findPlayersByTeam(teamId)));
    }
}
