package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readLines(String path) {
        try {
            Path file = Paths.get(path);
            if(!Files.exists(file)) {
                if(file.getParent() != null) {
                    Files.createDirectories(file.getParent());
                }
                Files.createFile(file);
                return new ArrayList<>();
            }
            return Files.readAllLines(file);
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 오류: " + e.getMessage());
        }
    }

    public static void writeLines(String path, List<String>lines) {
        try {
            Files.write(Paths.get(path), lines);
        } catch (IOException e) {
            throw new RuntimeException("파일 쓰기 오류: " + e.getMessage(), e);
        }
    }
}
