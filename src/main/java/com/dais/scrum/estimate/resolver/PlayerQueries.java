package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.service.PlayerService;

import java.util.UUID;

public interface PlayerQueries {

    PlayerService getPlayerService();

    default Result<Player> findById(UUID playerId) {
        return getPlayerService().findById(playerId);
    }

    default Result<Player> findByEmail(String emailAddress) {
        return getPlayerService().findByEmail(emailAddress);
    }

    default Result<Player> findByUsername(String username) {
        return getPlayerService().findByUsername(username);
    }

    default ListResult<Player> findPlayersByTeam(UUID teamId) {
        return new ListResult<>(getPlayerService().findPlayersByTeam(teamId));
    }
}
