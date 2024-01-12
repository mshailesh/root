package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBUtil {

    public static List<Document> executeQuery(MongoDatabase database, String collectionName, String queryJson) {
        List<Document> results = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = Document.parse(queryJson);

        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while (cursor.hasNext()) {
                results.add(cursor.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
