package guru.nidi.atlassian.remote.meta.client;

import guru.nidi.atlassian.remote.meta.ResponseMessage;

/**
 *
 */
public class JiraExportClientException extends RuntimeException {
    private final ResponseMessage responseMessage;

    public JiraExportClientException(String message, ResponseMessage responseMessage) {
        super(message + (responseMessage == null ? "" : ("\n" + responseMessage.getText())));
        this.responseMessage = responseMessage;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }
}
