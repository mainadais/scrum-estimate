package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Participant;
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
public class ParticipantRepositoryTest {

    @Autowired
    ParticipantRepository participantRepository;

    @Test
    public void testFindAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        participantRepository.findAll().forEach(participants::add);
        assertThat(participants.size(), Is.is(4));
    }
}