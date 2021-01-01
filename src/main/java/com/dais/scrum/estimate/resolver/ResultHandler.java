package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.domain.ListResult;
import com.dais.scrum.estimate.domain.Result;

public interface ResultHandler {

    default <T> Result<T> sendResponse(Result<T> response) {
        if (response.getError() != null) {
            throw new ResultException(1, response.getError());
        }
        return response;
    }

    default <T> ListResult<T> sendResponse(ListResult<T> response) {
        if (response.getError() != null) {
            throw new ResultException(1, response.getError());
        }
        return response;
    }
}
