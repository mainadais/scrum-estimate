package com.dais.scrum.estimate.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table("tbl_team")
@Data
@Builder
public class Team {

    @Id
    private UUID id;
    private String name;
    private String organization;
    private UUID organizer;
    private List<Participant> participants;
    private Date dateCreated;
}
