package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.repository.PlayerRepository;
import com.dais.scrum.estimate.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    final PlayerRepository playerRepository;

    @Override
    public Result<Player> findById(UUID playerId) {
        try {
            Optional<Player> find = playerRepository.findById(playerId);
            if (find.isPresent()) {
                return new Some<>(find.get());
            }
            return new None<>("No user was found with the id provided");
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> findByEmail(String emailAddress) {
        try {
            return new Some<>(playerRepository.findPlayerByEmail(emailAddress));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> findByUsername(String username) {
        try {
            return new Some<>(playerRepository.findPlayerByUsername(username));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> addAccount(AddAccount addAccount) {
        try {
            Result<Player> find = findById(addAccount.getPlayer());
            if (find.getData() != null) {
                Player player = addAccount.apply(find.getData());
                return new Some<>(playerRepository.save(player));
            }
            return find;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> updatePassword(UpdatePassword updatePassword) {
        try {
            Result<Player> find = findById(updatePassword.getPlayer());
            if (find.getData() != null) {
                Player player = updatePassword.apply(find.getData());
                return new Some<>(playerRepository.save(player));
            }
            return find;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> createPlayer(CreatePlayer createPlayer) {
        try {
            return new Some<>(playerRepository.save(createPlayer.get()));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> updatePlayer(UpdatePlayer updatePlayer) {
        try {
            Result<Player> find = findById(updatePlayer.getPlayer());
            if (find.getData() != null) {
                Player player = updatePlayer.apply(find.getData());
                return new Some<>(playerRepository.save(player));
            }
            return find;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> updateStatus(UpdateStatus updateStatus) {
        try {
            Result<Player> find = findById(updateStatus.getPlayer());
            if (find.getData() != null) {
                Player player = updateStatus.apply(find.getData());
                return new Some<>(playerRepository.save(player));
            }
            return find;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> updateRole(UpdateRole updateRole) {
        try {
            Result<Player> find = findById(updateRole.getPlayer());
            if (find.getData() != null) {
                Player player = updateRole.apply(find.getData());
                return new Some<>(playerRepository.save(player));
            }
            return find;
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<List<Player>> findPlayersByTeam(UUID teamId) {
        try {
            return new Some<>(playerRepository.findPlayersByTeam(teamId));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Integer> deletePlayer(UUID teamId) {
        try {
            playerRepository.deleteById(teamId);
            return new Some<>(1);
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }
}
