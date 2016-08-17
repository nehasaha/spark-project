package com.window;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.windows.spark.App.copyPath;
import static com.windows.spark.App.templatePath;

public class sparkTest {

    @Test
    public void copyImages() {
        deleteDirectory(copyPath());
        createDirectory(copyPath());
        copyFile(templatePath().resolve("canyon.png"), copyPath().resolve("canyon.png"));
        copyFile(templatePath().resolve("mountain.png"), copyPath().resolve("mountain.png"));
    }

    public static void copyFile(Path source, Path destination) {
        try {
            FileUtils.copyFile(source.toFile(), destination.toFile());
        }
        catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }

    public static void deleteDirectory(Path directory) {
        try {
            FileUtils.deleteDirectory(directory.toFile());
        }
        catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }

    public static void createDirectory(Path directory) {
        try {
            Files.createDirectories(directory);
        }
        catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }
}
