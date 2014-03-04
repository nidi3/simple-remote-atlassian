package guru.nidi.atlassian.remote;

import org.apache.axis.AxisFault;

/**
 *
 */
public class AtlassianException extends RuntimeException {
    public AtlassianException(String message, Throwable cause) {
        super(createMessage(message, cause), cause);
    }

    private static String createMessage(String message, Throwable cause) {
        if (cause instanceof AxisFault) {
            return message + " " + ((AxisFault) cause).getFaultString();
        }
        return message;
    }
}
