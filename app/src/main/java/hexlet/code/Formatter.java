package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {
    public static String format(List<DifferNode> nodes, String formatName) {
        return switch (formatName) {
            case "plain" -> PlainFormatter.format(nodes);
            case "stylish" -> StylishFormatter.format(nodes);
            case "json" -> new JsonFormatter().format(nodes);
            default -> throw new IllegalArgumentException("Unknown formatName: " + formatName);
        };
    }
}
