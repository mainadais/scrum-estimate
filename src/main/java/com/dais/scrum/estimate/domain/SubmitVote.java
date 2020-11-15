package com.dais.scrum.estimate.domain;

import com.dais.scrum.estimate.entity.Vote;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.Supplier;

@Data
public class SubmitVote implements Supplier<Vote> {

    private UUID participant;
    private String scrum;
    private BigDecimal vote;
    private UUID voteId;

    @Override
    public Vote get() {
        return Vote.builder()
                .participant(getParticipant())
                .scrum(getScrum())
                .vote(getVote())
                .id(getVoteId())
                .build();
    }
}
