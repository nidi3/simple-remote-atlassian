package stni.atlassian.remote.script;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class RemoteJira {
    private final HttpClient client;
    private final String serverUrl;
    private final String username;
    private final String password;
    private final String token;


    public RemoteJira(String serverUrl, String username, String password) throws RpcException {
        client = new HttpClient();
        this.serverUrl = serverUrl;
        this.username = username;
        this.password = password;
        token = (String) executeImpl("login", username, password);
    }

    public Object getServerInfo() throws RpcException {
        return executeImpl("getServerInfo",token);
    }

    public Object getProjectsNoSchemes() throws RpcException {
        return executeImpl("getProjectsNoSchemes",token);
    }
    public Object getVersions(String projectKey) throws RpcException {
        return executeImpl("getVersions",token,projectKey);
    }

    Object executeImpl(String command, Object... parameters) throws RpcException {
        PostMethod post = new PostMethod(serverUrl + "/rpc/json-rpc/jirasoapservice-v2/" + command);
        post.setRequestHeader("Content-Type", "application/json");
        post.setRequestHeader("Authorization", "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes()));
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, parameters);
            post.setRequestEntity(new ByteArrayRequestEntity(baos.toByteArray()));
            int status = client.executeMethod(post);
            if (status != HttpStatus.SC_OK) {
                throw new IOException("not ok: " + status + " " + post.getResponseBodyAsString());
            }
            try {
                ErrorResponse errorResponse = mapper.readValue(post.getResponseBodyAsString(), ErrorResponse.class);
                throw new RpcException(errorResponse);
            } catch (JsonParseException e) {
                //ignore
            } catch (JsonMappingException e) {
                //ignore
            }
            try {
                return mapper.readValue(post.getResponseBodyAsString(), new TypeReference<HashMap<String, Object>>() {
                });
            } catch (JsonParseException e) {
                //ignore
            } catch (JsonMappingException e) {
                //ignore
            }
            try {
                return mapper.readValue(post.getResponseBodyAsString(), new TypeReference<ArrayList<Object>>() {
                });
            } catch (JsonParseException e) {
                //ignore
            } catch (JsonMappingException e) {
                //ignore
            }
            return mapper.readValue(post.getResponseBodyAsString(), String.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
