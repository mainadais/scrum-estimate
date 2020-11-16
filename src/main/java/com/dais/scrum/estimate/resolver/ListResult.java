package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.Result;
import lombok.Data;

import java.util.List;

@Data
public class ListResult<T> {

    protected List<T> data;
    protected String error;

    public ListResult(List<T> result) {
        this.data = result;
        this.error = null;
    }

    public ListResult(Result<List<T>> result) {
        this.data = result.getData();
        this.error = result.getError();
    }
}
