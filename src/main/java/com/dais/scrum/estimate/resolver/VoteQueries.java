package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.VoteService;
import org.springframework.security.access.prepost.PreAuthorize;

public interface VoteQueries {

    VoteService getVoteService();

    @PreAuthorize("isAuthenticated()")
    default ListResult<Vote> getScrumVotes(String scrum) {
        return new ListResult<>(getVoteService().getScrumVotes(scrum));
    }
}
