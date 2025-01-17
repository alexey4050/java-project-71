package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parseFile(String filePath) throws Exception {
        String extension = getExtension(filePath);

        if (extension.equals("json")) {
            return parseJsonFile(filePath);
        } else if (extension.equals("yaml") || (extension.equals("yml"))) {
            return parseYamlFile(filePath);
        } else {
            throw new Exception("File format not correct: " + extension);
        }
    }

    public static Map<String, Object> parseJsonFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist.");
        }
        return JSON_MAPPER.readValue(Files.readString(path), new TypeReference<>() {
        });
    }

    public static void createJsonFile(String filePath, Map<String, Object> content) throws Exception {
        String jsonContent = JSON_MAPPER.writeValueAsString(content);
        Path writefilePath = Paths.get(filePath);
        Files.writeString(writefilePath, jsonContent);
    }

    public static Map<String, Object> parseYamlFile(String filePath) throws Exception {
        File file = Paths.get(filePath).toFile();
        if (!file.exists()) {
            throw new Exception("File '" + filePath + "' does not exist.");
        }
        return YAML_MAPPER.readValue(file, new TypeReference<>() {
        });
    }

    public static void createYamlFile(String filePath, Map<String, Object> content) throws Exception {
        File file = new File(filePath);
        YAML_MAPPER.writeValue(file, content);
    }

    private static String getExtension(String filePath) {
        String fileName = new File(filePath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);

    }
}

