package com.dais.scrum.estimate.client;

import com.dais.graphql.client.api.Configuration;
import com.dais.graphql.client.api.ExecutionResult;
import com.dais.graphql.client.api.GraphQLClient;
import com.dais.scrum.estimate.security.JwtUserDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PlayerMutationsClient {

    final GraphQLClient client;

    public ResponseEntity<ExecutionResult<JwtUserDetails>> login(String emailAddress, String password) {
        String operationName = "login";
        String loginQuery = "mutation login ($emailAddress: String!, $password: String!){\n" +
                "  login( emailAddress: $emailAddress, password: $password){\n" +
                "        username\n" +
                "    \t\tstatus\n" +
                "    \t\trole\n" +
                "    \t\ttoken\n" +
                "    }\n" +
                "}";
        Map<String, Object> variables = new HashMap<>();
        variables.put("emailAddress", emailAddress);
        variables.put("password", password);
        return client.executePost(Configuration::okHttpClient, Request.Builder::build, loginQuery, operationName, variables,
                new TypeReference<ExecutionResult<JwtUserDetails>>() {
                }).handle((res, th) -> {
            if (th != null || (res.getErrors() != null && !res.getErrors().isEmpty())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
            return ResponseEntity.ok(res);
        }).join();
    }
}
