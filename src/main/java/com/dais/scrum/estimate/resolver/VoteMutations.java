package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.domain.SubmitVote;
import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.VoteService;
import org.springframework.security.access.prepost.PreAuthorize;

public interface VoteMutations {

    VoteService getVoteService();

    @PreAuthorize("isAuthenticated()")
    default Result<Vote> submitVote(SubmitVote submitVote) {
        return getVoteService().submitVote(submitVote);
    }

    @PreAuthorize("isAuthenticated()")
    default void publishVotes(String scrum) {
        getVoteService().publishVotes(scrum);
    }
}
