package com.dais.scrum.estimate.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("tbl_participant")
@Data
@Builder
public class Participant {

    @Id
    private UUID id;
    private UUID player;
    private UUID team;
}
