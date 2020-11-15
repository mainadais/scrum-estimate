package com.dais.scrum.estimate.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("tbl_player")
@Data
public class Player {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date dateCreated;
}
