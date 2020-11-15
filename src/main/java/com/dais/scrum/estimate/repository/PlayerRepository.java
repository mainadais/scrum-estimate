package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends CrudRepository<Player, UUID> {

    @Query("select pl.* from tbl_participant pt inner join tbl_player pl on pt.player = pl.id where pt.team = :teamId")
    List<Player> findPlayersByTeam(@Param("teamId") UUID teamId);

    @Query("select id from tbl_player where email_address = :email")
    UUID findPlayerIdByEmail(@Param("email") String emailAddress);
}
