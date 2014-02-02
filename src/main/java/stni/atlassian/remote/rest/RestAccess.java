package stni.atlassian.remote.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class RestAccess {
    private static final int MAX_RESPONSE_SIZE = 1024 * 1024;

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
        client = new HttpClient();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public PostMethod post(String url) {
        return addHeaders(new PostMethod(baseUrl + url));
    }

    public GetMethod get(String url) {
        return addHeaders(new GetMethod(baseUrl + url));
    }

    public <T extends HttpMethodBase> T addHeaders(T method) {
        method.setRequestHeader("Content-Type", "application/json");
        method.setRequestHeader("Accept", "application/json");
        try {
            method.setRequestHeader("Authorization", "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("utf-8 encoding not found");
        }
        return method;
    }

    public Object executePost(String command, Object parameters) throws RestException, IOException {
        return executeImpl(preparePost(command, parameters));
    }

    public <T> T executePost(String command, Object parameters, Class<T> type) throws RestException, IOException {
        return executeImpl(preparePost(command, parameters), null, type);
    }

    private PostMethod preparePost(String command, Object parameters) throws RestException {
        PostMethod post = post(command);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, parameters);
        } catch (Exception e) {
            throw new RestException("error serializing parameters", e);
        }
        post.setRequestEntity(new ByteArrayRequestEntity(baos.toByteArray()));
        return post;
    }

    public Object executeGet(String command) throws RestException, IOException {
        GetMethod get = get(command);
        return executeImpl(get);
    }

    public <T> T executeGet(String command, Map<String, Object> parameters, Class<T> type) throws RestException, IOException {
        GetMethod get = get(command);
        return executeImpl(get, parameters, type);
    }

    private Object executeImpl(HttpMethodBase method) throws IOException, RestException {
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

    private <T> T executeImpl(HttpMethodBase method, Map<String, Object> parameters, Class<T> type) throws IOException, RestException {
        method.setQueryString(createQuery(parameters));
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

    private NameValuePair[] createQuery(Map<String, Object> parameters) {
        if (parameters == null) {
            return new NameValuePair[0];
        }
        NameValuePair[] res = new NameValuePair[parameters.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            res[i++] = new NameValuePair(entry.getKey(), entry.getValue().toString());
        }
        return res;
    }

    private String doExecute(HttpMethodBase method) throws IOException, RestException {
        int status = client.executeMethod(method);
        String resString = method.getResponseBodyAsString(MAX_RESPONSE_SIZE);
        if (status != HttpStatus.SC_OK) {
            try {
                Map<String, Object> errorMsg = mapper.readValue(resString, MAP_TYPE_REFERENCE);
                throw new RestException(errorMsg);
            } catch (JsonProcessingException e) {
                throw new RestException(resString, e);
            }
        }
        return resString;
    }

    public int executeMethod(HttpMethod method) throws IOException {
        return client.executeMethod(method);
    }

    public <T> T readResponse(HttpMethod method, TypeReference ref) throws IOException {
        return (T) mapper.readValue(method.getResponseBodyAsStream(), ref);
    }

}