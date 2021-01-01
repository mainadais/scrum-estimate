package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.service.VoteService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface VoteMutations extends ResultHandler {

    VoteService getVoteService();

    @PreAuthorize("isAuthenticated()")
    default void publishVotes(UUID teamId) {
        getVoteService().publishVotes(teamId);
    }
}
