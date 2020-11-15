package com.dais.scrum.estimate.service;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerService {

    Result<Player> findById(UUID playerId);

    Result<Player> findByEmail(String emailAddress);

    Result<Player> findByUsername(String username);

    Result<Player> addAccount(AddAccount addAccount);

    Result<Player> updatePassword(UpdatePassword updatePassword);

    Result<Player> createPlayer(CreatePlayer createPlayer);

    Result<Player> updatePlayer(UpdatePlayer updatePlayer);

    Result<Player> updateStatus(UpdateStatus updateStatus);

    Result<Player> updateRole(UpdateRole updateRole);

    Result<List<Player>> findPlayersByTeam(UUID teamId);

    Result<Integer> deletePlayer(UUID playerId);
}
