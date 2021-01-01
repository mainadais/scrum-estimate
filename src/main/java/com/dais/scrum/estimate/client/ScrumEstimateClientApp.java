package com.dais.scrum.estimate.client;

import com.dais.graphql.client.api.ExecutionResult;
import com.dais.graphql.client.api.GraphQLClient;
import com.dais.scrum.estimate.client.config.ClientConfig;
import com.dais.scrum.estimate.security.JwtUserDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ScrumEstimateClientApp {

    public static void main(String[] args) {
        boolean tokenOnly = args.length > 0 && "tokenOnly".equals(args[0]);
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientConfig.class);
        GraphQLClient plainClient = ac.getBean("plainClient", GraphQLClient.class);
        login(plainClient);

        if (!tokenOnly) {
            GraphQLClient tokenClient = ac.getBean(GraphQLClient.class);
            findByEmail(tokenClient);
        }
    }

    public static void login(GraphQLClient client) {
        PlayerMutationsClient playerMutationsClient = new PlayerMutationsClient(client);
        ResponseEntity<ExecutionResult<JwtUserDetails>> login = playerMutationsClient.login("steve_email@email.com", "password");
        System.out.println(login);
    }

    public static void findByEmail(GraphQLClient client) {
        PlayerQueriesClient playerQueriesClient = new PlayerQueriesClient(client);
        ResponseEntity<ExecutionResult<Map<String, Object>>> player = playerQueriesClient.findByEmail("steve_email@email.com", "query findByEmail($emailAddress: String!){\n" +
                "    findByEmail(emailAddress: $emailAddress){\n" +
                "        data{\n" +
                "            id\n" +
                "            emailAddress\n" +
                "            firstName\n" +
                "            lastName\n" +
                "            role\n" +
                "            dateCreated\n" +
                "            account {\n" +
                "                id\n" +
                "                username\n" +
                "                status\n" +
                "            }\n" +
                "        }\n" +
                "        error\n" +
                "    }\n" +
                "}");
        System.out.println(player);
    }
}
