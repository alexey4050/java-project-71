package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = Parser.parseFile(filepath1);
        Map<String, Object> map2 = Parser.parseFile(filepath2);

        return generateDiff(map1, map2);
    }


    public static String generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder output = new StringBuilder("{\n");

        allKeys.stream().forEach(key -> {
            boolean inFirstFile = map1.containsKey(key);
            boolean inSecondFile = map2.containsKey(key);
            if (inFirstFile && inSecondFile) {
                if (!map1.get(key).equals(map2.get(key))) {
                    output.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    output.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
                } else {
                    output.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
                }
            } else if (inFirstFile) {
                output.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else {
                output.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            }
        });
        output.append("}");
        return output.toString();
    }
}
