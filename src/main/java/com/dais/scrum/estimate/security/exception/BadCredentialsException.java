package com.dais.scrum.estimate.security.exception;

import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@RequiredArgsConstructor
public class BadCredentialsException extends RuntimeException {

    private final String email;

    @Override
    public String getMessage() {
        return MessageFormat.format("Email or password did not match for ''{0}''", email);
    }
}
