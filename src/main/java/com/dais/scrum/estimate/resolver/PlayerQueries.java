package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.service.PlayerService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface PlayerQueries {

    PlayerService getPlayerService();

    @PreAuthorize("isAuthenticated()")
    default Result<Player> findById(UUID playerId) {
        return getPlayerService().findById(playerId);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> findByEmail(String emailAddress) {
        return getPlayerService().findByEmail(emailAddress);
    }

    @PreAuthorize("isAuthenticated()")
    default Result<Player> findByUsername(String username) {
        return getPlayerService().findByUsername(username);
    }

    @PreAuthorize("isAuthenticated()")
    default ListResult<Player> findPlayersByTeam(UUID teamId) {
        return new ListResult<>(getPlayerService().findPlayersByTeam(teamId));
    }
}
