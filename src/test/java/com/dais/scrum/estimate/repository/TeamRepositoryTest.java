package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.entity.Team;
import graphql.kickstart.spring.web.boot.GraphQLWebsocketAutoConfiguration;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class, GraphQLWebsocketAutoConfiguration.class})
public class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void testFindAllTeams() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        assertThat(teams.size(), Is.is(8));
    }

    @Test
    public void testFindTeamsByOrganizer() {
        Player player = playerRepository.findPlayerByEmail("cassie_email@email.com");
        List<Team> teams = teamRepository.findTeamsByOrganizer(player.getId());
        assertThat(teams.size(), Is.is(2));
    }

    @Test
    public void testFindTeamsJoined() {
        Player player = playerRepository.findPlayerByEmail("cassie_email@email.com");
        List<Team> joined = teamRepository.findTeamsJoined(player.getId());
        assertThat(joined.size(), Is.is(1));
    }
}