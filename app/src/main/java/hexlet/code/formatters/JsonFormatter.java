package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public String format(List<Map<String, Object>> differences) {
        try {
            return OBJECT_MAPPER.writeValueAsString(differences);
        } catch (Exception e) {
            throw new RuntimeException("Failed to format to JSON", e);
        }
    }
}
