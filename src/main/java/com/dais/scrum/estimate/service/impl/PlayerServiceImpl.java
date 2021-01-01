package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Account;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.repository.PlayerRepository;
import com.dais.scrum.estimate.service.PlayerService;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    final PlayerRepository playerRepository;
    final PasswordEncoder passwordEncoder;
    @Qualifier("recoveryCache")
    final Cache<String, RecoverLogin> recoveryCache;

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
            Player player = playerRepository.findPlayerByEmail(emailAddress);
            Account account = playerRepository.findPlayerAccount(player.getId());
            player.setAccount(account);
            return new Some<>(player);
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> findByUsername(String username) {
        try {
            Player player = playerRepository.findPlayerByUsername(username);
            Account account = playerRepository.findPlayerAccount(player.getId());
            player.setAccount(account);
            return new Some<>(player);
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Player> addAccount(AddAccount addAccount) {
        try {
            addAccount.setPassword(passwordEncoder.encode(addAccount.getPassword()));
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
                updatePassword.setNewPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
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
    public Result<Integer> deletePlayer(UUID playerId) {
        try {
            playerRepository.deleteById(playerId);
            return new Some<>(1);
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<Recovery> recoverLogin(RecoverLogin recoverLogin) {
        try {
            RecoverLogin cached = recoveryCache.getIfPresent(recoverLogin.getEmailAddress());
            if (cached == null) {
                UUID emailToken = UUID.randomUUID();
                recoverLogin.setEmailToken(emailToken);
                recoveryCache.put(recoverLogin.getEmailAddress(), recoverLogin);
                //send email with recovery link
                System.out.printf("Recovery email token - %s%n", emailToken);
                return new Some<>(new Recovery("Email sent with recovery link"));
            } else if (cached.getEmailToken().equals(recoverLogin.getEmailToken())) {
                Result<Player> playerResult = findByEmail(recoverLogin.getEmailAddress());
                if (playerResult.getData() != null) {
                    Player player = playerResult.getData();
                    player.getAccount().setPassword(passwordEncoder.encode(recoverLogin.getNewPassword()));
                    try {
                        playerRepository.save(player);
                        recoveryCache.invalidate(recoverLogin.getEmailAddress());
                        return new Some<>(new Recovery("Password updated successfully"));
                    } catch (Exception e) {
                        recoveryCache.invalidate(recoverLogin.getEmailAddress());
                        return new None<>(String.format("Recovery failed - %s", e.getMessage()));
                    }
                } else {
                    recoveryCache.invalidate(recoverLogin.getEmailAddress());
                    return new None<>("No user available with supplied email");
                }
            } else {
                recoveryCache.invalidate(recoverLogin.getEmailAddress());
                return new None<>("Recovery token supplied does not match that sent to your email");
            }
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }
}
