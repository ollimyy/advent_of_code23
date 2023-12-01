package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class InputUtil {

    public static List<String> readAllLinesFromFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}