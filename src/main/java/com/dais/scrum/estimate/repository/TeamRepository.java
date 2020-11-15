package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Team;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends CrudRepository<Team, UUID> {

    @Query("select * from tbl_team where organizer = :organizer and name = :name")
    Team findByOrganizer(@Param("organizer") UUID organizerId, @Param("name") String name);
}
