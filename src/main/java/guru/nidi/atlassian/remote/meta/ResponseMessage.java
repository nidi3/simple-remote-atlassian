package guru.nidi.atlassian.remote.meta;

/**
 *
 */
public class ResponseMessage {
    public enum Type {
        SUCCESS, WARN, ERROR
    }

    private Type type;
    private boolean success;
    private String text;

    public ResponseMessage() {
    }

    public ResponseMessage(Type type, String text) {
        this.type = type;
        this.success = (type == Type.SUCCESS);
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "type=" + type +
                ", success=" + success +
                ", text='" + text + '\'' +
                '}';
    }
}
