package stni.atlassian.remote.queryold.expression;

/**
 *
 */
public class ExpressionException extends RuntimeException {
    public ExpressionException(Throwable cause) {
        super(cause);
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
