package utils;

public final class DataStoreManager {
    public enum DataStoreType {
        JSON("File (JSON)", JSONDataStore.getInstance()),
        MONGO_DB("Database (MongoDB)", MongoDBDataStore.getInstance());

        public final String label;
        public final IDataStore instance;

        DataStoreType(String label, IDataStore instance) {
            this.label = label;
            this.instance = instance;
        }

        public String toString() {
            return this.label;
        }
    }

    private static DataStoreType dataStoreType = DataStoreType.JSON;

    public static IDataStore getInstance() {
        return dataStoreType.instance;
    }

    public static void setDataStoreType(DataStoreType dataStoreType) {
        DataStoreManager.dataStoreType = dataStoreType;
    }

    private DataStoreManager() { }
}
