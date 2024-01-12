package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        MongoClient mongoClient = null;
        try {
            // Load properties from file
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("mongo-queries.properties");
            if (inputStream == null) {
                throw new IllegalStateException("config.properties file not found");
            }
            properties.load(inputStream);

            // Initialize MongoDB Client and Database
            String uri = properties.getProperty("mongodb.uri");
            String databaseName = properties.getProperty("mongodb.database");
            mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Fetch collection name and query from properties
            String collectionName = properties.getProperty("mongodb.collection");
            String queryJson = properties.getProperty("mongodb.query");

            // Use MongoDBUtil to execute query
            List<Document> results = MongoDBUtil.executeQuery(database, collectionName, queryJson);

            for (Document doc : results) {
                System.out.println(doc.toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
