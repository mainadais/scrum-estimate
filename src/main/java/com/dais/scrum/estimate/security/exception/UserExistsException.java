package com.dais.scrum.estimate.security.exception;

import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@RequiredArgsConstructor
public class UserExistsException extends RuntimeException {

    private final String email;

    @Override
    public String getMessage() {
        return MessageFormat.format("A user already exists with email ''{0}''", email);
    }
}
