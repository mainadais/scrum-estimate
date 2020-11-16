package com.dais.scrum.estimate.service.impl;

import com.dais.scrum.estimate.domain.None;
import com.dais.scrum.estimate.domain.Result;
import com.dais.scrum.estimate.domain.Some;
import com.dais.scrum.estimate.domain.SubmitVote;
import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.repository.VoteRepository;
import com.dais.scrum.estimate.service.VoteService;
import com.dais.scrum.estimate.service.VotesPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    final VoteRepository voteRepository;
    final VotesPublisher votesPublisher;

    @Override
    public Result<Vote> submitVote(SubmitVote submitVote) {
        try {
            return new Some<>(voteRepository.save(submitVote.get()));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Result<List<Vote>> getScrumVotes(String scrum) {
        try {
            return new Some<>(voteRepository.findVotesByScrum(scrum));
        } catch (Exception e) {
            return new None<>(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    @Override
    public Boolean publishVotes(String scrum) {
        try {
            Result<List<Vote>> votes = getScrumVotes(scrum);
            votesPublisher.accept(votes.getData());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
