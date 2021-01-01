package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.*;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.service.PlayerService;
import com.dais.scrum.estimate.service.TeamService;
import graphql.kickstart.spring.web.boot.GraphQLWebsocketAutoConfiguration;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
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
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class, GraphQLWebsocketAutoConfiguration.class})
class TeamServiceImplTest {

    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;

    @Test
    void findById() {
        Result<Player> player = playerService.findByEmail("cassie_email@email.com");
        Result<Team> team = teamService.findTeamByName(player.getData().getId(), "cassie_team_1");
        assertThat(team.getData(), IsNull.notNullValue());
        assertThat(team.getData().getName(), Is.is("cassie_team_1"));
    }

    @Test
    void createAndUpdateTeam() {
        Result<Player> player = playerService.findByEmail("cassie_email@email.com");
        CreateTeam newTeam = new CreateTeam();
        newTeam.setDateCreated(new Date());
        newTeam.setChoices(new String[]{"1", "2", "3", "5", "8", "13", "21"});
        newTeam.setName("morning blitz");
        newTeam.setOrganizer(player.getData().getId());

        Result<Team> team = teamService.createTeam(newTeam);
        assertThat(team.getData().getId(), IsNull.notNullValue());
        assertThat(team.getData().getName(), Is.is(newTeam.getName()));

        //update team
        UpdateTeam updateTeam = new UpdateTeam();
        updateTeam.setName("afternoon waltz");
        updateTeam.setChoices(new String[]{"1", "2", "3", "5", "8", "13", "21"});
        updateTeam.setTeam(team.getData().getId());

        Result<Team> updatedTeam = teamService.updateTeam(updateTeam);
        assertThat(updatedTeam.getData().getName(), Is.is(updateTeam.getName()));
        assertThat(updatedTeam.getData().getChoices(), Is.is(updateTeam.getChoices()));
    }

    @Test
    void addDropAndClearParticipants() {
        Result<Player> player = playerService.findByEmail("steve_email@email.com");
        Result<Team> team = teamService.findTeamByName(player.getData().getId(), "steve_team_1");
        Result<List<Player>> teamPlayers = playerService.findPlayersByTeam(team.getData().getId());
        assertThat(teamPlayers.getData().size(), Is.is(0));

        AddParticipant add1 = new AddParticipant();
        add1.setPlayer(player.getData().getId());
        add1.setTeam(team.getData().getId());
        Result<Team> with1Participant = teamService.addParticipant(add1);
        assertThat(with1Participant.getData().getParticipants().size(), Is.is(1));

        Result<Player> player2 = playerService.findByEmail("jacob_email@email.com");
        AddParticipant add2 = new AddParticipant();
        add2.setPlayer(player2.getData().getId());
        add2.setTeam(team.getData().getId());
        Result<Team> with2Participants = teamService.addParticipant(add2);
        assertThat(with2Participants.getData().getParticipants().size(), Is.is(2));

        //remove participant
        DropParticipant drop1 = new DropParticipant();
        drop1.setPlayer(player2.getData().getId());
        drop1.setTeam(team.getData().getId());
        Result<Team> dropped1Participant = teamService.dropParticipant(drop1);
        assertThat(dropped1Participant.getData().getParticipants().size(), Is.is(1));

        //clear participants
        Result<Team> clearedParticipants = teamService.clearParticipants(team.getData().getId());
        assertThat(clearedParticipants.getData().getParticipants().size(), Is.is(0));
    }

    @Test
    void dropTeam() {
        Result<Player> player = playerService.findByEmail("melissa_email@email.com");
        Result<Team> team = teamService.findTeamByName(player.getData().getId(), "melissa_team_1");
        Result<Integer> dropped = teamService.dropTeam(team.getData().getId());
        assertThat(dropped.getData(), Is.is(1));
    }
}