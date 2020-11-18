package com.dais.scrum.estimate.client;

import com.dais.graphql.client.api.Configuration;
import com.dais.graphql.client.api.ExecutionResult;
import com.dais.graphql.client.api.GraphQLClient;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlayerQueriesClient {

    final GraphQLClient client;

    public ResponseEntity<ExecutionResult<Map<String, Object>>> findById(UUID playerId, String queryString) {
        String operationName = "findById";
        Map<String, Object> variables = new HashMap<>();
        variables.put("playerId", playerId);
        return getExecutionResponseEntity(queryString, operationName, variables);
    }

    public ResponseEntity<ExecutionResult<Map<String, Object>>> findByEmail(String emailAddress, String queryString) {
        String operationName = "findByEmail";
        Map<String, Object> variables = new HashMap<>();
        variables.put("emailAddress", emailAddress);
        return getExecutionResponseEntity(queryString, operationName, variables);
    }

    public ResponseEntity<ExecutionResult<Map<String, Object>>> findByUsername(String username, String queryString) {
        String operationName = "findByUsername";
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);
        return getExecutionResponseEntity(queryString, operationName, variables);
    }

    public ResponseEntity<ExecutionResult<Map<String, Object>>> findPlayersByTeam(UUID teamId, String queryString) {
        String operationName = "findByEmail";
        Map<String, Object> variables = new HashMap<>();
        variables.put("teamId", teamId);
        return getExecutionResponseEntity(queryString, operationName, variables);
    }

    private ResponseEntity<ExecutionResult<Map<String, Object>>> getExecutionResponseEntity(String queryString, String operationName, Map<String, Object> variables) {
        return client.executePost(Configuration::okHttpClient, Request.Builder::build, queryString, operationName, variables,
                new TypeReference<ExecutionResult<Map<String, Object>>>() {
                }).handle((res, th) -> {
            if (th != null || (res.getErrors() != null && !res.getErrors().isEmpty())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            return ResponseEntity.ok(res);
        }).join();
    }
}
