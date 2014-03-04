package guru.nidi.atlassian.remote.script;

/**
 *
 */
public class RpcException extends Exception {
    private final ErrorResponse errorResponse;

    public RpcException(ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    @Override
    public String toString() {
        return "RpcException{" +
                "errorResponse=" + errorResponse +
                '}';
    }
}
