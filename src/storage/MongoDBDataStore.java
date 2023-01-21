package storage;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MongoDB Data Store
 * <p>
 * Type of data store that uses a MongoDB database to store data.
 * <p>
 * Patterns:
 * 1. Adapter: Implements the IDataStoreAdapter interface.
 * 2. Singleton: Only one instance of this data store can exist.
 */
public class MongoDBDataStore implements IDataStoreAdapter {
    private static MongoDBDataStore instance = null;
    private final MongoDatabase db;

    private MongoDBDataStore() {
        db = new MongoClient().getDatabase("database");
    }

    public static MongoDBDataStore getInstance() {
        if (instance == null) instance = new MongoDBDataStore();
        return instance;
    }

    @Override
    public <T> void addToCollection(String collectionName, T element, Class<T> clazz) {
        createCollection(collectionName);
        var gson = new Gson();
        var newDoc = Document.parse(gson.toJson(element));
        db.getCollection(collectionName).insertOne(newDoc);
    }

    @Override
    public <T> List<T> getCollection(String collectionName, Class<T> clazz) {
        createCollection(collectionName);
        var gson = new Gson();
        var docs = db.getCollection(collectionName).find();
        var collection = new ArrayList<T>();
        for (var doc : docs) {
            collection.add(gson.fromJson(doc.toJson(), clazz));
        }
        return collection;
    }

    private void createCollection(String collectionName) {
        boolean collectionExists = false;
        for (var name : db.listCollectionNames()) {
            if (Objects.equals(name, collectionName)) {
                collectionExists = true;
                break;
            }
        }
        if (!collectionExists) db.createCollection(collectionName);
    }
}
