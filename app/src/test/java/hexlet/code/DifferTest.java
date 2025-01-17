package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("A special test case")
class DifferTest {

    private static final String FILEPATH1 = "src/test/resources/filepathtest1.json";
    private static final String FILEPATH2 = "src/test/resources/filepathtest2.json";

    @BeforeEach
    @DisplayName("Remove files before each test ✂️")
    public void setUp() throws Exception {
        Files.deleteIfExists(Paths.get(FILEPATH1));
        Files.deleteIfExists(Paths.get(FILEPATH2));
    }

    @Test
    public void tetsCreateFile() throws Exception {
        Map<String, Object> content = new HashMap<>();
        content.put("key", "value");
        Parser.createJsonFile(FILEPATH1, content);

        String jsonContent = new String(Files.readAllBytes(Paths.get(FILEPATH1)));
        String expected = "{\"key\":\"value\"}";
        assertEquals(expected, jsonContent);
    }

    @Test
    public void testParseJsonFile() throws Exception {
        Map<String, Object> content = new HashMap<>();
        content.put("key", "value");
        Parser.createJsonFile(FILEPATH1, content);

        Map<String, Object> parsedContent = Parser.parseJsonFile(FILEPATH1);
        assertEquals(content, parsedContent);
    }

    @Test
    public void testGenerateEqualJson() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key", "value");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key", "value");

        Parser.createJsonFile(FILEPATH1, content1);
        Parser.createJsonFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n    key: value\n}";
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerateDifferentValues() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key", "value1");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key", "value2");

        Parser.createJsonFile(FILEPATH1, content1);
        Parser.createJsonFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key: value1\n  + key: value2\n}";
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerateDifferentKeys() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key1", "value1");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key2", "value2");

        Parser.createJsonFile(FILEPATH1, content1);
        Parser.createJsonFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key1: value1\n  + key2: value2\n}";
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateEmptyFiles() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        Map<String, Object> content2 = new HashMap<>();

        Parser.createJsonFile(FILEPATH1, content1);
        Parser.createJsonFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n}";
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateMultipleDifferences() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key1", "value1");
        content1.put("key2", "value2");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key2", "value2");
        content2.put("key3", "value3");

        Parser.createJsonFile(FILEPATH1, content1);
        Parser.createJsonFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key1: value1\n    key2: value2\n  + key3: value3\n}";
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerateDifferentDataTypes() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key", true);
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key", false);

        Parser.createJsonFile(FILEPATH1, content1);
        Parser.createJsonFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key: true\n  + key: false\n}";
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateYaml() throws Exception {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("host", "hexlet.io");
        data1.put("timeout", 50);
        data1.put("proxy", "123.234.53.22");
        data1.put("follow", false);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("timeout", 20);
        data2.put("verbose", true);
        data2.put("host", "hexlet.io");

        Parser.createYamlFile("src/test/resources/filePath1.yml", data1);
        Parser.createYamlFile("src/test/resources/filePath2.yml", data2);

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Differ.generate("src/test/resources/filePath1.yml", "src/test/resources/filePath2.yml");
        assertEquals(expected.trim(), actual.trim());

    }
}
