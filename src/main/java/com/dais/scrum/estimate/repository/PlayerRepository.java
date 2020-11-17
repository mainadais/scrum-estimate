package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Account;
import com.dais.scrum.estimate.entity.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends CrudRepository<Player, UUID> {

    @Query("select * from tbl_participant pt left outer join tbl_player pl on pt.player = pl.id where pt.team = :teamId")
    List<Player> findPlayersByTeam(@Param("teamId") UUID teamId);

    @Query("select * from tbl_player pl where pl.email_address = :email")
    Player findPlayerByEmail(@Param("email") String emailAddress);

    @Query("select * from tbl_player pl left outer join tbl_account acc on acc.player = pl.id where acc.username = :username")
    Player findPlayerByUsername(@Param("username") String username);

    @Query("select * from tbl_account acc where acc.player = :playerId")
    Account findPlayerAccount(@Param("playerId") UUID playerId);
}
