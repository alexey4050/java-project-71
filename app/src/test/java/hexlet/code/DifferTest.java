package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {
    private static String expectedPlain;
    private static String expectedStylish;
    private static String expectedJson;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedJson = readFixture("json_expected.json");
        expectedPlain = readFixture("plain_expected.txt");
        expectedStylish = readFixture("stylish_expected.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(expectedStylish);
        assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualTo(expectedStylish);
        assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualTo(expectedPlain);
        assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualTo(expectedJson);
    }
}

