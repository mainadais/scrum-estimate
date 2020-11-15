package com.dais.scrum.estimate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Result<T> {

    protected T data;
    protected String error;

    protected Result(T data) {
        this.data = data;
    }
}
