package com.ebac.practica60MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    public static MongoDatabase createConnection() {
        try {
            String connectionUrl = "mongodb://root:root@localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionUrl);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("practica60");
            return mongoDatabase;
        } catch (Exception e) {
            throw new RuntimeException("No se puede conectar con la BD", e);
        }

    }
}
