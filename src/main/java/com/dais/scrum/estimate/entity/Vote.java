package com.dais.scrum.estimate.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("tbl_vote")
@Data
@Builder
public class Vote {

    @Id
    private UUID id;
    private UUID participant;
    private String scrum;
    private BigDecimal vote;
}
