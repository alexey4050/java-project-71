package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void createFile(String filePath, Map<String, Object> content) throws Exception {
        String jsonContent = objectMapper.writeValueAsString(content);
        Path writefilePath = Paths.get(filePath);
        Files.writeString(writefilePath, jsonContent);
    }

    public static Map<String, Object> parseJsonFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist.");
        }
        return objectMapper.readValue(Files.readString(path), Map.class);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> config1 = parseJsonFile(filepath1);
        Map<String, Object> config2 = parseJsonFile(filepath2);
        return "comparing files: " + config1 + " and " + config2;
    }
}
