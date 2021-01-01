package com.dais.scrum.estimate.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class RecoverLogin {

    private String emailAddress;
    private UUID emailToken;
    private String newPassword;
}
