/*
 * Copyright (C) 2013 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guru.nidi.atlassian.remote.script;

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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class RemoteJira {
    private static final int MAX_RESPONSE_SIZE = 1024 * 1024;

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
        HttpUtils.setAuthHeader(post, username, password);
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mapper.writeValue(baos, parameters);
            post.setRequestEntity(new ByteArrayRequestEntity(baos.toByteArray()));
            int status = client.executeMethod(post);
            if (status != HttpStatus.SC_OK) {
                throw new IOException("not ok: " + status + " " + post.getResponseBodyAsString(MAX_RESPONSE_SIZE));
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
            try {
                return mapper.readValue(post.getResponseBodyAsString(MAX_RESPONSE_SIZE), new TypeReference<ArrayList<Object>>() {
                });
            } catch (JsonParseException e) {
                //ignore
            } catch (JsonMappingException e) {
                //ignore
            }
            return mapper.readValue(post.getResponseBodyAsString(MAX_RESPONSE_SIZE), String.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
