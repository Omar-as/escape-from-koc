package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * JSON Data Store
 * <p>
 * Type of data store that uses files in JSON format to store data.
 * <p>
 * Patterns:
 * 1. Adapter: Implements the IDataStoreAdapter interface.
 * 2. Singleton: Only one instance of this data store can exist.
 */
public class JSONDataStore implements IDataStoreAdapter {
    private static JSONDataStore instance = null;

    private JSONDataStore() {
    }

    public static JSONDataStore getInstance() {
        if (instance == null) instance = new JSONDataStore();
        return instance;
    }

    private static <T> List<T> readStoreCollection(String collectionName, Class<T> clazz) {
        var collectionJSON = readStoreFile(collectionName);
        if (collectionJSON.isEmpty()) collectionJSON = "[]"; // Empty collection
        var gson = new Gson();
        var collectionType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(collectionJSON, collectionType);
    }

    private static <T> void writeStoreCollection(String collectionName, List<T> collection) {
        var gson = new Gson();
        var collectionJSON = gson.toJson(collection);
        writeStoreFile(collectionName, collectionJSON);
    }

    private static String readStoreFile(String fileName) {
        var storeFile = getStoreFile(fileName);
        try {
            var scanner = new Scanner(storeFile);
            var lines = new ArrayList<String>();
            while (scanner.hasNextLine()) lines.add(scanner.nextLine());
            scanner.close();
            return String.join("\n", lines);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeStoreFile(String fileName, String content) {
        var storeFile = getStoreFile(fileName);
        try {
            var fileWriter = new FileWriter(storeFile, false);
            var printWriter = new PrintWriter(fileWriter);
            printWriter.println(content);
            fileWriter.close();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getStoreFile(String fileName) {
        createStoreFileIfNotExists(fileName);
        return new File(getStoreFilePath(fileName));
    }

    private static void createStoreFileIfNotExists(String fileName) {
        createStoreDirIfNotExists();
        var storeFile = new File(getStoreFilePath(fileName));
        try {
            storeFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createStoreDirIfNotExists() {
        try {
            Files.createDirectories(Paths.get(getStoreDirPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getStoreFilePath(String fileName) {
        return Constants.CONFIG_FILE_PATH.formatted(Constants.HOME_DIRECTORY, "%s.json".formatted(fileName));
    }

    private static String getStoreDirPath() {
        return Constants.CONFIG_DIR_PATH.formatted(Constants.HOME_DIRECTORY);
    }

    @Override
    public <T> void addToCollection(String collectionName, T element, Class<T> clazz) {
        var collection = JSONDataStore.readStoreCollection(collectionName, clazz);
        collection.add(element);
        JSONDataStore.writeStoreCollection(collectionName, collection);
    }

    @Override
    public <T> List<T> getCollection(String collectionName, Class<T> clazz) {
        return JSONDataStore.readStoreCollection(collectionName, clazz);
    }
}
