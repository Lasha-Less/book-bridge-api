package com.lb.book_bridge_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoConnectionTest implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    public MongoConnectionTest(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            var collections = mongoTemplate.getCollectionNames();
            System.out.println("✅ MongoDB connection successful. Collections: " + collections);
        } catch (Exception e) {
            System.err.println("❌ MongoDB connection failed: " + e.getMessage());
        }
    }
}
