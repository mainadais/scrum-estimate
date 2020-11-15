package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.entity.Team;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void testFindAllPlayers() {
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        assertThat(players.size(), Is.is(4));
    }

    @Test
    public void testFindPlayersByTeam(){
        UUID organizer = playerRepository.findPlayerIdByEmail("cassie_email@email.com");
        Team cassieTeam = teamRepository.findByOrganizer(organizer);
        List<Player> players = playerRepository.findPlayersByTeam(cassieTeam.getId());
        assertThat(players.size(), Is.is(4));
    }
}