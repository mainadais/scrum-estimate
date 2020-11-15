package com.dais.scrum.estimate.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("tbl_team")
@Data
public class Team {

    @Id
    private UUID id;
    private String name;
    private String organization;
    @Column("player")
    private UUID organizer;
    private Date dateCreated;
}
