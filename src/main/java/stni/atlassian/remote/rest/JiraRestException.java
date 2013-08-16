package stni.atlassian.remote.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JiraRestException extends Exception {
    private final Map<String, Object> values;
    private List<String> errorMessages = Collections.emptyList();

    public JiraRestException(String message) {
        this(message, null);
    }

    public JiraRestException(String message, Throwable cause) {
        super(cause);
        values = new HashMap<String, Object>();
        errorMessages = Collections.singletonList(message);
    }

    public JiraRestException(Map<String, Object> values) {
        this.values = values;
        Object errorMessageObj = values.get("errorMessages");
        if (errorMessageObj instanceof List) {
            errorMessages = (List<String>) errorMessageObj;
        }
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public String getMessage() {
        return errorMessages.toString();
    }
}
