package stni.atlassian.remote.rest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JiraRestAccess {
    public static final TypeReference<HashMap<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<HashMap<String, Object>>() {
    };
    public static final TypeReference<ArrayList<Object>> LIST_TYPE_REFERENCE = new TypeReference<ArrayList<Object>>() {
    };
    public static final TypeReference<HashMap<String, ArrayList<IssueLinkType>>> MAP_WITH_LINKTYPES_TYPE_REFERENCE = new TypeReference<HashMap<String, ArrayList<IssueLinkType>>>() {
    };

    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String baseUrl;
    private final String username;
    private final String password;

    public JiraRestAccess(String baseUrl, String username, String password) {
        client = new HttpClient();
        mapper = new ObjectMapper();
        this.baseUrl = baseUrl + "/rest/api/2/";
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
        try {
            method.setRequestHeader("Authorization", "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("utf-8 encoding not found");
        }
        return method;
    }

    public Object executePost(String command, Object parameters) throws JiraRestException, IOException {
        PostMethod post = post(command);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, parameters);
        } catch (Exception e) {
            throw new JiraRestException("error serializing parameters", e);
        }
        post.setRequestEntity(new ByteArrayRequestEntity(baos.toByteArray()));
        return executeImpl(post);
    }

    public Object executeGet(String command) throws JiraRestException, IOException {
        GetMethod get = get(command);
        return executeImpl(get);
    }

    private Object executeImpl(HttpMethod method) throws IOException, JiraRestException {
        int status = client.executeMethod(method);
        String resString = method.getResponseBodyAsString();
        if (status != HttpStatus.SC_OK) {
            try {
                Map<String, Object> errorMsg = mapper.readValue(resString, MAP_TYPE_REFERENCE);
                throw new JiraRestException(errorMsg);
            } catch (JsonProcessingException e) {
                throw new JiraRestException(resString, e);
            }
        }

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

    public int executeMethod(HttpMethod method) throws IOException {
        return client.executeMethod(method);
    }

    public <T> T readResponse(HttpMethod method, TypeReference ref) throws IOException {
        return (T)mapper.readValue(method.getResponseBodyAsStream(), ref);
    }

}