package com.dais.scrum.estimate.service;

import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.domain.SubmitVote;
import com.dais.scrum.estimate.entity.Vote;

import java.util.List;

public interface VoteService {

    Result<Vote> submitVote(SubmitVote submitVote);

    Result<List<Vote>> getScrumVotes(String scrum);

    Boolean publishVotes(String scrum);
}
