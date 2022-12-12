package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public final class ConfigManager {

    private ConfigManager() {
    }

    public static ArrayList<String> readConfigFile(String fileName) {
        var configFile = getConfigFile(fileName);
        try {
            var scanner = new Scanner(configFile);
            var lines = new ArrayList<String>();
            while (scanner.hasNextLine()) lines.add(scanner.nextLine());
            scanner.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeLineToConfigFile(String fileName, String line) {
        var configFile = getConfigFile(fileName);
        try {
            var printWriter = new PrintWriter(configFile);
            printWriter.println(line);
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getConfigFile(String fileName) {
        createConfigFileIfNotExists(fileName);
        return new File(getConfigFilePath(fileName));
    }

    private static void createConfigFileIfNotExists(String fileName) {
        createConfigDirIfNotExists();
        var configFile = new File(getConfigFilePath(fileName));
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createConfigDirIfNotExists() {
        try {
            Files.createDirectories(Paths.get(getConfigDirPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getConfigFilePath(String fileName) {
        return Constants.CONFIG_FILE_PATH.formatted(Constants.HOME_DIRECTORY, fileName);
    }

    private static String getConfigDirPath() {
        return Constants.CONFIG_DIR_PATH.formatted(Constants.HOME_DIRECTORY);
    }
}
