package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.domain.AddAccount;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.entity.Team;
import graphql.kickstart.spring.web.boot.GraphQLWebsocketAutoConfiguration;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class, GraphQLWebsocketAutoConfiguration.class})
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void testCreatePlayer() {
        Player player = playerRepository.save(Player.builder()
                .emailAddress("player@email.com")
                .lastName("player_last")
                .firstName("player_first")
                .dateCreated(new Date())
                .build());
        assertThat(player.getId(), IsNull.notNullValue());

        //update player account info
        AddAccount addAccount = new AddAccount();
        addAccount.setPassword("player_password");
        addAccount.setUsername("player_username");
        addAccount.apply(player);
        player = playerRepository.save(player);
        assertThat(player.getAccount(), IsNull.notNullValue());
        assertThat(player.getAccount().getUsername(), Is.is("player_username"));
    }

    @Test
    public void testFindAllPlayers() {
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        assertThat(players.size(), Is.is(5));
    }

    @Test
    public void testFindPlayersByTeam() {
        Player organizer = playerRepository.findPlayerByEmail("cassie_email@email.com");
        Team cassieTeam = teamRepository.findTeamByName(organizer.getId(), "cassie_team_1");
        List<Player> players = playerRepository.findPlayersByTeam(cassieTeam.getId());
        assertThat(players.size(), Is.is(4));
    }

    @Test
    public void testFindPlayerByEmail() {
        Player player = playerRepository.findPlayerByEmail("cassie_email@email.com");
        assertThat(player.getFirstName(), Is.is("cassie_first"));
    }

    @Test
    public void testFindPlayerByUsername() {
        Player player = playerRepository.findPlayerByUsername("cassie");
        assertThat(player.getEmailAddress(), Is.is("cassie_email@email.com"));
    }
}