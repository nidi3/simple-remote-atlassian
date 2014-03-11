package guru.nidi.atlassian.remote;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpRequestBase;

import java.nio.charset.Charset;

/**
 *
 */
public abstract class HttpUtils {
    private HttpUtils() {
    }

    public static void setAuthHeader(HttpRequestBase req, String username, String password) {
        req.setHeader("Authorization", "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes(Charset.forName("utf-8"))));
    }

}
