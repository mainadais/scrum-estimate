package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.VoteService;

public interface VoteQueries {

    VoteService getVoteService();

    default ListResult<Vote> getScrumVotes(String scrum) {
        return new ListResult<>(getVoteService().getScrumVotes(scrum));
    }
}
