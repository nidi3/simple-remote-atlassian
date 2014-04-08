package guru.nidi.atlassian.remote.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.atlassian.remote.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class RestAccess {
    public static final TypeReference<HashMap<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<HashMap<String, Object>>() {
    };
    public static final TypeReference<ArrayList<Object>> LIST_TYPE_REFERENCE = new TypeReference<ArrayList<Object>>() {
    };

    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String baseUrl;
    private final String username;
    private final String password;

    public RestAccess(String baseUrl, String username, String password) {
        client = new DefaultHttpClient();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public HttpPost post(String url) {
        return addHeaders(new HttpPost(baseUrl + url));
    }

    public HttpGet get(String url) {
        return addHeaders(new HttpGet(baseUrl + url));
    }

    public <T extends HttpRequestBase> T addHeaders(T method) {
        method.setHeader("Content-Type", "application/json");
        method.setHeader("Accept", "application/json");
        HttpUtils.setAuthHeader(method, username, password);
        return method;
    }

    public Object executePost(String command, Object parameters) throws RestException, IOException {
        return executeImpl(preparePost(command, parameters));
    }

    public <T> T executePost(String command, Object parameters, Class<T> type) throws RestException, IOException {
        return executeImpl(preparePost(command, parameters), type);
    }

    private HttpPost preparePost(String command, Object parameters) throws RestException {
        HttpPost post = post(command);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, parameters);
        } catch (Exception e) {
            throw new RestException("error serializing parameters", e);
        }
        post.setEntity(new ByteArrayEntity(baos.toByteArray()));
        return post;
    }


    public Object executeGet(String command) throws RestException, IOException {
        HttpGet get = get(command);
        return executeImpl(get);
    }

    public <T> T executeGet(String command, Map<String, Object> parameters, Class<T> type) throws RestException, IOException {
        return executeImpl(prepareGet(command, parameters), type);
    }

    private HttpGet prepareGet(String command, Map<String, Object> parameters) throws RestException {
        HttpGet get = get(command);
        try {
            get.setURI(addQuery(new URIBuilder(get.getURI()), parameters).build());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
        return get;
    }

    private URIBuilder addQuery(URIBuilder builder, Map<String, Object> parameters) {
        if (parameters != null) {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                String value;
                if (entry.getValue() instanceof List) {
                    value = StringUtils.join((List<?>) entry.getValue(), ",");
                } else if (entry.getValue().getClass().isArray()) {
                    value = StringUtils.join((Object[]) entry.getValue(), ",");
                } else {
                    value = entry.getValue().toString();
                }
                builder.addParameter(entry.getKey(), value);
            }
        }
        return builder;
    }

    private Object executeImpl(HttpRequestBase method) throws IOException, RestException {
        String resString = doExecute(method);

        if (resString.length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(resString, MAP_TYPE_REFERENCE);
        } catch (JsonParseException e) {
            //ignore
        } catch (JsonMappingException e) {
            //ignore
        }
        return mapper.readValue(resString, LIST_TYPE_REFERENCE);
    }

    private <T> T executeImpl(HttpRequestBase method, Class<T> type) throws IOException, RestException {
        String resString = doExecute(method);

        if (resString.length() == 0) {
            return null;
        }
        try {
            return mapper.readValue(resString, type);
        } catch (JsonParseException e) {
            throw new RestException(resString, e);
        } catch (JsonMappingException e) {
            throw new RestException(resString, e);
        }
    }

    private String doExecute(HttpRequestBase method) throws IOException, RestException {
        HttpResponse response = client.execute(method);
        String resString = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            try {
                Map<String, Object> errorMsg = mapper.readValue(resString, MAP_TYPE_REFERENCE);
                throw new RestException(errorMsg);
            } catch (JsonProcessingException e) {
                throw new RestException(resString, e);
            }
        }
        return resString;
    }

    public HttpResponse executeMethod(HttpUriRequest method) throws IOException {
        return client.execute(method);
    }

    public <T> T readResponse(HttpResponse response, TypeReference ref) throws IOException {
        return (T) mapper.readValue(EntityUtils.toString(response.getEntity()), ref);
    }

}