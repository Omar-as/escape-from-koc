package managers;

import storage.IDataStoreAdapter;
import storage.JSONDataStore;
import storage.MongoDBDataStore;

/**
 * Data Store Manager
 * <p>
 * Handles storing data on disk.
 * It uses a data store based on the user's choice.
 * <p>
 * Patterns:
 * 1. Adapter  : Works with any type of Data Store.
 * 2. Singleton: Only one instance of the Data Store can be created.
 */
public final class DataStoreManager {
    private static DataStoreType dataStoreType = DataStoreType.JSON;

    private DataStoreManager() {
    }

    public static IDataStoreAdapter getInstance() {
        return dataStoreType.instance;
    }

    public static void setDataStoreType(DataStoreType dataStoreType) {
        DataStoreManager.dataStoreType = dataStoreType;
    }

    public enum DataStoreType {
        JSON("File (JSON)", JSONDataStore.getInstance()),
        MONGO_DB("Database (MongoDB)", MongoDBDataStore.getInstance());

        public final String label;
        public final IDataStoreAdapter instance;

        DataStoreType(String label, IDataStoreAdapter instance) {
            this.label = label;
            this.instance = instance;
        }

        public String toString() {
            return this.label;
        }
    }
}
