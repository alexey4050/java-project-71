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
                + "stylish_expected.txt")); // Читаем ожидаемый результат из файла
        String actualStylish = Differ.generate(fixturesPath
                + "file1.yml", fixturesPath + "file2.yml", "stylish");
        assertEquals(expectedStylish.trim(), actualStylish.trim());
    }
}

