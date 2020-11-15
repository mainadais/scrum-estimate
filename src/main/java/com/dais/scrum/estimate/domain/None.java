package com.dais.scrum.estimate.domain;

public class None<T> extends Result<T> {

    public None(String error) {
        super(null, error);
    }
}
