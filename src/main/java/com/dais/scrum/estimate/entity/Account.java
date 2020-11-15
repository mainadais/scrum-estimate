package com.dais.scrum.estimate.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("tbl_account")
@Data
@Builder
public class Account {

    @Id
    private UUID id;
    private String username;
    private String password;
    private Status status;
    public enum Status {active, deactivated, pending_confirmation}
}
