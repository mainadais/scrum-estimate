package com.dais.scrum.estimate.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("tbl_player")
@Data
@Builder
public class Player {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Account account;
    private Role role;
    private Date dateCreated;

    public enum Role {player, admin, guest}
}
