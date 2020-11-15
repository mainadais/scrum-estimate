package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.domain.SubmitVote;
import com.dais.scrum.estimate.entity.Participant;
import com.dais.scrum.estimate.entity.Player;
import com.dais.scrum.estimate.entity.Team;
import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.PlayerService;
import com.dais.scrum.estimate.service.TeamService;
import com.dais.scrum.estimate.service.VoteService;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
class VoteServiceImplTest {

    @Autowired
    VoteService voteService;
    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;

    @Test
    void submitVote() {
        Result<Player> player = playerService.findByEmail("cassie_email@email.com");
        Result<Team> team = teamService.findByOrganizer(player.getData().getId(), "cassie_team_1");
        Result<List<Vote>> votes = voteService.getScrumVotes("cassie scrum");

        //submit votes
        for (Participant participant : team.getData().getParticipants()) {
            Optional<Vote> voteId = votes.getData() != null ? votes.getData().stream().filter(vote -> vote.getParticipant().equals(participant.getId())).findFirst() : Optional.empty();
            SubmitVote vote = new SubmitVote();
            vote.setParticipant(participant.getId());
            vote.setScrum("cassie scrum");
            vote.setVote(new BigDecimal(10));
            vote.setVoteId(voteId.map(Vote::getId).orElse(null));
            Result<Vote> submitted = voteService.submitVote(vote);
            assertThat(submitted.getData(), IsNull.notNullValue());
            assertThat(submitted.getData().getParticipant(), Is.is(participant.getId()));
        }

        //updated team
        Result<List<Vote>> updatedTeam = voteService.getScrumVotes("cassie scrum");
        assertThat(updatedTeam.getData().size(), Is.is(4));
    }

    @Test
    void getScrumVotes() {
        Result<List<Vote>> votes = voteService.getScrumVotes("cassie scrum");
        assertThat(votes.getData().size(), Is.is(4));
    }
}