package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.domain.SubmitVote;
import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.VoteService;

public interface VoteMutations {

    VoteService getVoteService();

    default Result<Vote> submitVote(SubmitVote submitVote) {
        return getVoteService().submitVote(submitVote);
    }

    default void publishVotes(String scrum){ getVoteService().publishVotes(scrum);}
}
