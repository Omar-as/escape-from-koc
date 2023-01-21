package utils;

public final class DataStoreManager {
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

    private static DataStoreType dataStoreType = DataStoreType.JSON;

    public static IDataStoreAdapter getInstance() {
        return dataStoreType.instance;
    }

    public static void setDataStoreType(DataStoreType dataStoreType) {
        DataStoreManager.dataStoreType = dataStoreType;
    }

    private DataStoreManager() { }
}
