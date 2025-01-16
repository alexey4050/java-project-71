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
        Differ.createFile(FILEPATH1, content);

        String jsonContent = new String(Files.readAllBytes(Paths.get(FILEPATH1)));
        String expected = "{\"key\":\"value\"}";
        assertEquals(expected, jsonContent);
    }

    @Test
    public void testParseJsonFile() throws Exception {
        Map<String, Object> content = new HashMap<>();
        content.put("key", "value");
        Differ.createFile(FILEPATH1, content);

        Map<String, Object> parsedContent = Differ.parseJsonFile(FILEPATH1);
        assertEquals(content, parsedContent);
    }

    @Test
    public void testGenerate_EqualJson() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key", "value");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key", "value");

        Differ.createFile(FILEPATH1, content1);
        Differ.createFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n    key: value\n}";
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerate_DifferentValues() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key", "value1");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key", "value2");

        Differ.createFile(FILEPATH1, content1);
        Differ.createFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key: value1\n  + key: value2\n}";
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerate_DifferentKeys() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key1", "value1");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key2", "value2");

        Differ.createFile(FILEPATH1, content1);
        Differ.createFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key1: value1\n  + key2: value2\n}";
        assertEquals(expected, result);
    }

    @Test
    public void testGenerate_EmptyFiles() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        Map<String, Object> content2 = new HashMap<>();

        Differ.createFile(FILEPATH1, content1);
        Differ.createFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n}";
        assertEquals(expected, result);
    }

    @Test
    public void testGenerate_MultipleDifferences() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key1", "value1");
        content1.put("key2", "value2");
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key2", "value2");
        content2.put("key3", "value3");

        Differ.createFile(FILEPATH1, content1);
        Differ.createFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key1: value1\n    key2: value2\n  + key3: value3\n}";
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerate_DifferentDataTypes() throws Exception {
        Map<String, Object> content1 = new HashMap<>();
        content1.put("key", true);
        Map<String, Object> content2 = new HashMap<>();
        content2.put("key", false);

        Differ.createFile(FILEPATH1, content1);
        Differ.createFile(FILEPATH2, content2);

        String result = Differ.generate(FILEPATH1, FILEPATH2);
        String expected = "{\n  - key: true\n  + key: false\n}";
        assertEquals(expected, result);
    }
}