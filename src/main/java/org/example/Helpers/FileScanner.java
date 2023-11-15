package org.example.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanner {

    private static File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = FileScanner.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public static Scanner getScanner(String fileName) throws FileNotFoundException, URISyntaxException {
        File fileFromResources = getFileFromResource(fileName);

        return new Scanner(fileFromResources);
    }

    public static List<String> getLines(String fileName) throws FileNotFoundException, URISyntaxException {
        File fileFromResources = getFileFromResource(fileName);
        Scanner scanner = FileScanner.getScanner(fileName);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            lines.add(data);
        }
        scanner.close();
        return lines;
    }
}
