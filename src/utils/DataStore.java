package utils;

public class DataStore {
    private static IDataStore instance = null;

    public static IDataStore getInstance() {
        return instance;
    }

    private DataStore() { }
}
