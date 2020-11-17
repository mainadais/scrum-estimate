package com.dais.scrum.estimate.repository;

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

    @Test
    public void testFindAllTeams() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        assertThat(teams.size(), Is.is(8));
    }
}