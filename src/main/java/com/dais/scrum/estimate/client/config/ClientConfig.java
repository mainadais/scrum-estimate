package com.dais.scrum.estimate.client.config;

import com.dais.graphql.client.api.GraphQLClient;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Setter
public class ClientConfig {

    final String bearerToken = "ADD_TOKEN_HERE";
    @Value("${gql.server.app.host:localhost}")
    String host;
    @Value("${gql.server.app.port:7080}")
    int port;
    @Value("${gql.server.app.host.path:/graphql}")
    String path;

    @Bean("tokenClient")
    @Primary
    public GraphQLClient graphQLClient() {
        return new GraphQLClient(configuration(), host, port, path, false);
    }

    @Bean("plainClient")
    public GraphQLClient loginGraphQLClient() {
        return new GraphQLClient(host, port, path, false);
    }

    @Bean
    public com.dais.graphql.client.api.Configuration configuration() {
        return new com.dais.graphql.client.api.Configuration() {
            public OkHttpClient okHttpClient() {
                return new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor())
                        .addInterceptor(chain -> {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Authorization", String.format("Bearer %s", bearerToken))
                                    .build();
                            return chain.proceed(request);
                        })
                        .build();
            }
        };


    }
}
