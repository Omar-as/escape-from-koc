package storage;

import java.util.List;

/**
 * IDataStoreAdapter
 * <p>
 * Defines a common interface that all data stores should implement.
 * <p>
 * Patterns:
 * 1. Adapter: Any data store that implements this interface can be swapped with another one with ease.
 */
public interface IDataStoreAdapter {
    <T> void addToCollection(String collectionName, T element, Class<T> clazz);

    <T> List<T> getCollection(String collectionName, Class<T> clazz);
}
