package com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DatabaseConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = "mongodb://app_user:app_password@localhost:27017/traceability";
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "traceability");
    }
}
