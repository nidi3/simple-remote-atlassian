package stni.atlassian.remote.script;

/**
 *
 */
public class ErrorMessage {
    private String message;
    private String data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", data='" + data + '\'' +
                ", code=" + code +
                '}';
    }
}
