package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Vote;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends CrudRepository<Vote, UUID> {

    @Query("select * from tbl_vote where scrum = :scrum")
    List<Vote> findVotesByScrum(@Param("scrum") String scrum);
}
