package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Comparator {
    public static List<DifferNode> compare(Map<String, Object> file1, Map<String, Object> file2) {
        List<DifferNode> result = new ArrayList<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key : keys) {
            if (!file1.containsKey(key)) {
                result.add(new DifferNode(key, "added", null, file2.get(key)));
            } else if (!file2.containsKey(key)) {
                result.add(new DifferNode(key, "removed", file1.get(key), null));
            } else if (Objects.equals(file1.get(key), file2.get(key))) {
                result.add(new DifferNode(key, "same", file1.get(key), file2.get(key)));
            } else {
                result.add(new DifferNode(key, "updated", file1.get(key), file2.get(key)));
            }
        }
        return result;
    }
}
