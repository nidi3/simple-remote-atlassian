package guru.nidi.atlassian.remote.meta.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.atlassian.remote.HttpUtils;
import guru.nidi.atlassian.remote.meta.GenerateRequest;
import guru.nidi.atlassian.remote.meta.JiraGenerateRequest;
import guru.nidi.atlassian.remote.meta.ResponseMessage;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class JiraExportClient {
    private static class UrlHolder {
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    private static class RequestHolder {
        private final JiraGenerateRequest request;

        private RequestHolder(JiraGenerateRequest request) {
            this.request = request;
        }

        public GenerateRequest getRequest() {
            return request;
        }
    }

    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = new DefaultHttpClient();

    private final String serverUrl;
    private final String username;
    private final String password;


    public JiraExportClient(String serverUrl, String username, String password) {
        this.serverUrl = serverUrl;
        this.username = username;
        this.password = password;
    }

    public InputStream getExport(JiraGenerateRequest request) throws IOException {
        final String url = generateExport(request);
        return requestExport(url);
    }

    public void close() {
        client.getConnectionManager().shutdown();
    }

    private String generateExport(JiraGenerateRequest request) throws IOException {
        final String jsonRequest = mapper.writeValueAsString(new RequestHolder(request));
        final HttpPost post = new HttpPost(serverUrl + "/action/generate/jira");
        post.setEntity(new StringEntity(jsonRequest, ContentType.APPLICATION_JSON));
        HttpUtils.setAuthHeader(post, username, password);
        final HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throwClientException("Problem generating the export", response);
        }
        return mapper.readValue(response.getEntity().getContent(), UrlHolder.class).getUrl();
    }

    private void throwClientException(String message, HttpResponse response) throws IOException {
        message += ": " + response.getStatusLine().toString();
        final Header encoding = response.getEntity().getContentEncoding();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(response.getEntity().getContent(), out);
        String body = out.toString(encoding == null ? "utf-8" : encoding.getValue());
        try {
            throw new JiraExportClientException(message, mapper.readValue(body, ResponseMessage.class));
        } catch (JsonProcessingException e) {
            throw new JiraExportClientException(message + body, null);
        }
    }

    private void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[10000];
        int read;
        while ((read = in.read(buf)) > 0) {
            out.write(buf, 0, read);
        }
        in.close();
        out.close();
    }

    private InputStream requestExport(String url) throws IOException {
        final HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "application/octet-stream");
        HttpUtils.setAuthHeader(get, username, password);
        final HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throwClientException("Problem fetching the export", response);
        }
        return response.getEntity().getContent();
    }
}
