package com.dais.scrum.estimate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("tbl_participant")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    private UUID id;
    private UUID player;
    private UUID team;
}
