package hexlet.code.formatters;

import hexlet.code.Comparator;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> difference : differences) {
            String nodeOutput = processNode(difference);
            if (!nodeOutput.isEmpty()) {
                result.append(nodeOutput).append("\n");
            }
        }
        return result.toString().trim();
    }

    private static String processNode(Map<String, Object> difference) {
        String result = "";
        String property = (String) difference.get("name");
        String action = (String) difference.get("status");
        Object oldValue = difference.get("oldValue");
        Object newValue = difference.get("newValue");

        switch (action) {
            case Comparator.STATUS_UPDATE:
                result = String.format("Property '%s' was updated. From %s to %s", property,
                        formatValue(oldValue), formatValue(newValue));
                break;
            case Comparator.STATUS_REMOVED:
                result = String.format("Property '%s' was removed", property);
                break;
            case Comparator.STATUS_ADDED:
                result = String.format("Property '%s' was added with value: %s", property,
                        formatValue(newValue));
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
