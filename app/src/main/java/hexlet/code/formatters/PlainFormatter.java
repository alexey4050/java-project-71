package hexlet.code.formatters;

import hexlet.code.DifferNode;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DifferNode> nodes) {
        StringBuilder result = new StringBuilder();
        for (DifferNode node : nodes) {
            String nodeOutput = processNode(node);
            if (!nodeOutput.isEmpty()) {
                result.append(nodeOutput).append("\n");
            }
        }
        return result.toString().trim();
    }

    private static String processNode(DifferNode node) {
        String result = "";
        String property = node.getName();
        String action = node.getStatus();

        switch (action) {
            case "updated":
                result = String.format("Property '%s' was updated. From %s to %s", property,
                        formatValue(node.getOldValue()), formatValue(node.getNewValue()));
                break;
            case "removed":
                result = String.format("Property '%s' was removed", property);
                break;
            case "added":
                result = String.format("Property '%s' was added with value: %s", property,
                        formatValue(node.getNewValue()));
                break;
            default:
                break;
        }
        return result;
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof Boolean || value instanceof Integer) {
            return value.toString();
        }
        return "'" + value + "'";
    }
}
