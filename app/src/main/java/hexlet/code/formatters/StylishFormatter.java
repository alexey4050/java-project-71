package hexlet.code.formatters;

import hexlet.code.DifferNode;
import java.util.List;
import java.util.StringJoiner;
import java.util.Objects;
import java.util.Map;


public class StylishFormatter {
    public static String format(List<DifferNode> differences) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");

        for (DifferNode node : differences) {
            String name = node.getName();
            String oldValue = formatValue(node.getOldValue());
            String newValue = formatValue(node.getNewValue());
            String status = node.getStatus();

            switch (status) {
                case "updated":
                    result.add(" - " + name + ": " + oldValue);
                    result.add(" + " + name + ": " + newValue);
                    break;
                case "added":
                    result.add(" + " + name + ": " + newValue);
                    break;
                case "same":
                    result.add("   " + name + ": " + oldValue);
                    break;
                case "removed":
                    result.add(" - " + name + ": " + oldValue);
                    break;
                default:
                    break;
            }
        }
        result.add("}");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return value.toString();
        }
        return Objects.toString(value, "null");
    }
}
