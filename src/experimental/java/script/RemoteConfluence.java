package guru.nidi.atlassian.remote.script;

import guru.nidi.atlassian.remote.HttpUtils;
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
import java.util.Map;

/**
 *
 */
public class RemoteConfluence {
    private static final int MAX_RESPONSE_SIZE = 1024 * 1024;

    private final HttpClient client;
    private final String serverUrl;
    private final String username;
    private final String password;


    public RemoteConfluence(String serverUrl, String username, String password) {
        client = new HttpClient();
        this.serverUrl = serverUrl;
        this.username = username;
        this.password = password;
    }

    public Object getServerInfo() throws RpcException {
        return executeImpl("getServerInfo");
    }

    public Object getSpaces() throws RpcException {
        return executeImpl("getSpaces");
    }

    public Object getPages(String space) throws RpcException {
        return executeImpl("getPages", space);
    }

    public Object getPage(String space, String title) throws RpcException {
        return executeImpl("getPage", space, title);
    }

    public Object storePage(Map<String, Object> page) throws RpcException {
        return executeImpl("storePage", page);
    }

    public Object updatePage(Map<String, Object> page, Map<String, Object> pageUpdateOptions) throws RpcException {
        Map<String, Object> p = new HashMap<String, Object>(page);
        p.remove("current");
        return executeImpl("updatePage", page, pageUpdateOptions);
    }

    Object executeImpl(String command, Object... parameters) throws RpcException {
        PostMethod post = new PostMethod(serverUrl + "/rpc/json-rpc/confluenceservice-v2/" + command);
        post.setRequestHeader("Content-Type", "application/json");
        HttpUtils.setAuthHeader(post, username, password);
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, parameters);
            post.setRequestEntity(new ByteArrayRequestEntity(baos.toByteArray()));
            int status = client.executeMethod(post);
            if (status != HttpStatus.SC_OK) {
                throw new IOException("not ok");
            }
            try {
                ErrorResponse errorResponse = mapper.readValue(post.getResponseBodyAsString(MAX_RESPONSE_SIZE), ErrorResponse.class);
                throw new RpcException(errorResponse);
            } catch (JsonParseException e) {
                //ignore
            } catch (JsonMappingException e) {
                //ignore
            }
            try {
                return mapper.readValue(post.getResponseBodyAsString(MAX_RESPONSE_SIZE), new TypeReference<HashMap<String, Object>>() {
                });
            } catch (JsonParseException e) {
                //ignore
            } catch (JsonMappingException e) {
                //ignore
            }
            return mapper.readValue(post.getResponseBodyAsString(MAX_RESPONSE_SIZE), new TypeReference<ArrayList<Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
