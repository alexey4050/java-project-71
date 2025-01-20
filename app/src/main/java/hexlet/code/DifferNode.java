package hexlet.code;

public class DifferNode {
    private final String name;
    private final String status;
    private final Object oldValue;
    private final Object newValue;

    public DifferNode(String name, String status, Object oldValue, Object newValue) {
        this.name = name;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
