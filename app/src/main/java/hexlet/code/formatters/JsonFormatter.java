package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferNode;

import java.util.List;

public class JsonFormatter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public String format(List<DifferNode> nodes) {
        try {
            return OBJECT_MAPPER.writeValueAsString(nodes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to format to JSON", e);
        }
    }
}
