package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

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
    void testYamlPlain() throws Exception {
        String expectedPlain = Files.readString(Paths.get(fixturesPath + "plain_expected.txt"));
        String actualPlain = Differ.generate(fixturesPath
                + "file1.yml", fixturesPath + "file2.yml", "plain");
        assertEquals(expectedPlain.trim(), actualPlain.trim());
    }

    @Test
    void testYamlJson() throws Exception {
        String expectedJson = Files.readString(Paths.get(fixturesPath
                + "json_expected.json"));
        String actualJson = Differ.generate(fixturesPath
                + "file1.yml", fixturesPath + "file2.yml", "json");
        assertEquals(expectedJson.trim(), actualJson.trim());
    }

    @Test
    void testJsonStylish() throws Exception {
        String expectedStylish = Files.readString(Paths.get(fixturesPath
                + "stylish_expected.txt"));
        String actualStylish = Differ.generate(fixturesPath
                + "file1.json", fixturesPath + "file2.json", "stylish");
        assertEquals(expectedStylish.trim(), actualStylish.trim());
    }

    @Test
    void testJsonPlain() throws Exception {
        String expectedPlain = Files.readString(Paths.get(fixturesPath
                + "plain_expected.txt"));
        String actualPlain = Differ.generate(fixturesPath
                + "file1.json", fixturesPath + "file2.json", "plain");
        assertEquals(expectedPlain.trim(), actualPlain.trim());
    }

    @Test
    void testJsonJson() throws Exception {
        String expectedJson = Files.readString(Paths.get(fixturesPath
                + "json_expected.json"));
        String actualJson = Differ.generate(fixturesPath
                + "file1.json", fixturesPath + "file2.json", "json");
        assertEquals(expectedJson.trim(), actualJson.trim());
    }

    @Test
    void testDifferGenerateJsonFromYaml() throws Exception {
        String expectedJson = Files.readString(Paths.get(fixturesPath
                + "json_expected.json"));
        String actualJson = Differ.generate(fixturesPath + "file1.yml",
                fixturesPath + "file2.yml");
        assertEquals(expectedJson.trim(), actualJson.trim());
    }

    @Test
    void testDifferGeneratePlainFromYaml() throws Exception {
        String expectedJson = Files.readString(Paths.get(fixturesPath
                + "plain_expected.txt"));
        String actualJson = Differ.generate(fixturesPath + "file1.json",
                fixturesPath + "file2.json", "plain");
        assertEquals(expectedJson.trim(), actualJson.trim());
    }
}
