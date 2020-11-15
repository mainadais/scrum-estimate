package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Account;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.service.PlayerService;
import com.dais.scrum.estimate.service.TeamService;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
public class PlayerServiceImplTest {

    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;

    @Test
    void findByIdOrEmailOrUsername() {
        Result<Player> byEmail = playerService.findByEmail("steve_email@email.com");
        assertThat(byEmail.getData(), IsNull.notNullValue());
        assertThat(byEmail.getData().getEmailAddress(), Is.is("steve_email@email.com"));

        Result<Player> byUsername = playerService.findByUsername("steve");
        assertThat(byUsername.getData(), IsNull.notNullValue());

        Result<Player> byId = playerService.findById(byEmail.getData().getId());
        assertThat(byId.getData(), IsNull.notNullValue());
        assertThat(byId.getData().getId().toString(), Is.is(byUsername.getData().getId().toString()));
    }

    @Test
    @Disabled("skip test for now")
    void addAccount() {
        //create new player
        CreatePlayer createPlayer = new CreatePlayer();
        createPlayer.setEmailAddress("player_email@email.com");
        createPlayer.setFirstName("player_first");
        createPlayer.setLastName("player_last");

        //save new user
        Result<Player> newPlayer = playerService.createPlayer(createPlayer);

        //create account for new player
        AddAccount addAccount = new AddAccount();
        addAccount.setUsername("player_username");
        addAccount.setPassword("player_password");
        addAccount.setPlayer(newPlayer.getData().getId());

        //update account info for new player
        Result<Player> updatedPlayer = playerService.addAccount(addAccount);
        assertThat(updatedPlayer.getData(), IsNull.notNullValue());
        assertThat(updatedPlayer.getData().getAccount().getUsername(), Is.is("player_username"));
    }

    @Test
    void updatePassword() {
        Result<Player> player = playerService.findByUsername("cassie");
        assertThat(player.getData(), IsNull.notNullValue());

        String newPassword = "abc_xyz";
        UpdatePassword updatePassword = new UpdatePassword();
        updatePassword.setPassword(newPassword);
        updatePassword.setPlayer(player.getData().getId());
        Result<Player> updatedPlayer = playerService.updatePassword(updatePassword);
        assertThat(updatedPlayer.getData().getAccount().getPassword(), Is.is(newPassword));
    }

    @Test
    void createAndDeletePlayer() {
        CreatePlayer createPlayer = new CreatePlayer();
        createPlayer.setLastName("the_last_name");
        createPlayer.setFirstName("the_first_name");
        createPlayer.setEmailAddress("the_email@email.com");
        createPlayer.setDateCreated(new Date());

        Result<Player> player = playerService.createPlayer(createPlayer);
        assertThat(player.getData().getId(), IsNull.notNullValue());

        //delete player
        Result<Integer> deleted = playerService.deletePlayer(player.getData().getId());
        assertThat(deleted.getData(), Is.is(1));
    }

    @Test
    void updatePlayer() {
        Result<Player> player = playerService.findByUsername("cassie");
        assertThat(player.getData(), IsNull.notNullValue());

        UpdatePlayer updatePlayer = new UpdatePlayer();
        updatePlayer.setFirstName("jennie");
        updatePlayer.setLastName("bean");
        updatePlayer.setPlayer(player.getData().getId());

        Result<Player> updatedPlayer = playerService.updatePlayer(updatePlayer);
        assertThat(updatedPlayer.getData().getFirstName(), Is.is(updatePlayer.getFirstName()));
        assertThat(updatedPlayer.getData().getLastName(), Is.is(updatePlayer.getLastName()));

        UpdateRole updateRole = new UpdateRole();
        updateRole.setPlayer(player.getData().getId());
        updateRole.setRole(Player.Role.admin);

        Result<Player> updatedRole = playerService.updateRole(updateRole);
        assertThat(updatedRole.getData().getRole(), Is.is(updateRole.getRole()));

        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setPlayer(player.getData().getId());
        updateStatus.setStatus(Account.Status.active);

        Result<Player> updatedStatus = playerService.updateStatus(updateStatus);
        assertThat(updatedStatus.getData().getAccount().getStatus(), Is.is(updateStatus.getStatus()));
    }

    @Test
    void findPlayersByTeam() {
        Result<Player> player = playerService.findByUsername("cassie");
        assertThat(player.getData(), IsNull.notNullValue());

        Result<Team> team = teamService.findByOrganizer(player.getData().getId(), "cassie_team_1");
        Result<List<Player>> teamPlayers = playerService.findPlayersByTeam(team.getData().getId());
        assertThat(teamPlayers.getData().size(), Is.is(4));
    }
}