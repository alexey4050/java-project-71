package hexlet.code.formatters;

import hexlet.code.DifferNode;

import java.util.List;

public class Formatter {
    public static String format(List<DifferNode> nodes, String formatName) {
        return switch (formatName) {
            case "plain" -> PlainFormatter.format(nodes);
            case "stylish" -> StylishFormatter.format(nodes);
            default -> throw new IllegalArgumentException("Unknown formatName: " + formatName);
        };
    }
}
