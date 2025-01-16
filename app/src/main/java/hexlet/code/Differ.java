package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void createFile(String filePath, Map<String, Object> content) throws Exception {
        String jsonContent = OBJECT_MAPPER.writeValueAsString(content);
        Path writefilePath = Paths.get(filePath);
        Files.writeString(writefilePath, jsonContent);
    }

    public static Map<String, Object> parseJsonFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist.");
        }
        return OBJECT_MAPPER.readValue(Files.readString(path), new TypeReference<Map<String, Object>>() {
        });
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> config1 = Differ.parseJsonFile(filepath1);
        Map<String, Object> config2 = Differ.parseJsonFile(filepath2);

        Set<String> allKeys = new TreeSet<>(config1.keySet());
        allKeys.addAll(config2.keySet());

        StringBuilder output = new StringBuilder("{\n");

        allKeys.stream().forEach(key -> {
            boolean inFirstFile = config1.containsKey(key);
            boolean inSecondFile = config2.containsKey(key);
            if (inFirstFile && inSecondFile) {
                if (!config1.get(key).equals(config2.get(key))) {
                    output.append("  - ").append(key).append(": ").append(config1.get(key)).append("\n");
                    output.append("  + ").append(key).append(": ").append(config2.get(key)).append("\n");
                } else {
                    output.append("    ").append(key).append(": ").append(config1.get(key)).append("\n");
                }
            } else if (inFirstFile) {
                output.append("  - ").append(key).append(": ").append(config1.get(key)).append("\n");
            } else {
                output.append("  + ").append(key).append(": ").append(config2.get(key)).append("\n");
            }
        });
        output.append("}");
        return output.toString();
    }
}
