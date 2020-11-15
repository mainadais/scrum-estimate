package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Vote;
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
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
public class VoteRepositoryTest {

    @Autowired
    VoteRepository voteRepository;

    @Test
    public void testFindAllVotes() {
        List<Vote> votes = new ArrayList<>();
        voteRepository.findAll().forEach(votes::add);
        assertThat(votes.size(), Is.is(4));
    }

    @Test
    public void testFindVotesByScrum(){
        List<Vote> votes = voteRepository.findVotesByScrum("cassie scrum");
        assertThat(votes.size(), Is.is(4));
        votes = voteRepository.findVotesByScrum("cassie-no exist scrum");
        assertThat(votes.size(), Is.is(0));
    }
}