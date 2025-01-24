package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferNode;

import java.util.List;
public class JsonFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String format(List<DifferNode> nodes) {
        try {
            return objectMapper.writeValueAsString(nodes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to format to JSON", e);

        }
    }
}
