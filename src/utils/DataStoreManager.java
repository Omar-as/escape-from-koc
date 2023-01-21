package utils;

import java.lang.reflect.InvocationTargetException;

public final class DataStoreManager {
    private static IDataStore instance = JSONDataStore.getInstance();

    public static IDataStore getInstance() {
        return instance;
    }

    public static void setInstance(IDataStore store) {
        instance = store;
    }

    private DataStoreManager() { }
}
