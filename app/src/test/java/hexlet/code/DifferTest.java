package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private final String fixturesPath = "src/test/resources/";

    @Test
    void testYamlStylish() throws Exception {
        String expectedStylish = Files.readString(Paths.get(fixturesPath
                + "stylish_expected.txt"));
        String actualStylish = Differ.generate(fixturesPath
                + "file1.yml", fixturesPath + "file2.yml", "stylish");
        assertEquals(expectedStylish.trim(), actualStylish.trim());
    }

    @Test
    void testPlainFormatter() throws Exception {
        String expectedPlain = Files.readString(Paths.get(fixturesPath
                + "plain_expected.txt"));
        String actualPlain = Differ.generate(fixturesPath
                + "file1.json", fixturesPath + "file2.json", "plain");
        assertEquals(expectedPlain.trim(), actualPlain.trim());
    }

    @Test
    void testJsonFormatter() {
        JsonFormatter formatter = new JsonFormatter();

        DifferNode node1 = new DifferNode("property1", "updated",
                "oldValue1", "newValue1");
        DifferNode node2 = new DifferNode("property2", "removed",
                "oldValue2", null);
        List<DifferNode> nodes = Arrays.asList(node1, node2);
        String jsonOutput = formatter.format(nodes);
        String expectedJson = "[{\"name\":\"property1\",\"status\":\"updated\","
                + "\"oldValue\":\"oldValue1\",\"newValue\":\"newValue1\"},"
                + "{\"name\":\"property2\",\"status\":\"removed\","
                + "\"oldValue\":\"oldValue2\",\"newValue\":null}]";
        assertEquals(expectedJson, jsonOutput);
    }
}

