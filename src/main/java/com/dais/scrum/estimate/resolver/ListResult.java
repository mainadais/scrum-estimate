package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;

import java.util.List;

public class ListResult<T>  {

    protected List<T> data;
    protected String error;

    public ListResult(Result<List<T>> result) {
        this.data = result.getData();
        this.error = result.getError();
    }
}
