package com.dais.scrum.estimate.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("tbl_account")
@Data
public class Account {

    @Id
    private UUID id;
    private String username;
    private String password;
    private Player player;
}
