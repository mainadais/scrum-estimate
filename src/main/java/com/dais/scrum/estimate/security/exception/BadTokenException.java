package com.dais.scrum.estimate.security.exception;

public class BadTokenException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Token is invalid or expired";
    }
}
