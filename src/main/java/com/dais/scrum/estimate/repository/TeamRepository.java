package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Team;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends CrudRepository<Team, UUID> {

    @Query("select * from tbl_team where organizer = :organizer and name = :name")
    Team findTeamByName(@Param("organizer") UUID organizerId, @Param("name") String name);

    @Query("select * from tbl_team where organizer = :organizer")
    List<Team> findTeamsByOrganizer(@Param("organizer") UUID organizerId);

    @Query("select t.* from tbl_team t inner join tbl_participant p on t.id = p.team where p.player = :player")
    List<Team> findTeamsJoined(@Param("player") UUID playerId);
}
