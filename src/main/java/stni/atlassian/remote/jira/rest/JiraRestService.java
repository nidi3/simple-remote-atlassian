package stni.atlassian.remote.jira.rest;

import com.atlassian.jira.rpc.soap.beans.RemoteAttachment;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import stni.atlassian.remote.rest.RestException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 */
public class JiraRestService {
    private final JiraRestAccess access;

    public JiraRestService(String baseUrl, String username, String password) {
        access = new JiraRestAccess(baseUrl, username, password);
    }

    public Object executePost(String command, Object parameters) throws RestException, IOException {
        return access.executePost(command, parameters);
    }

    public Object executeGet(String command) throws RestException, IOException {
        return access.executeGet(command);
    }

    public List<IssueLinkType> getIssueLinkTypes() throws IOException, RestException {
        GetMethod method = access.get("issueLinkType");
        int status = access.executeMethod(method);
        if (status != HttpStatus.SC_OK) {
            Map<String, Object> errorMsg = access.readResponse(method, JiraRestAccess.MAP_TYPE_REFERENCE);
            throw new RestException(errorMsg);
        }

        Map<String, List<IssueLinkType>> res = access.readResponse(method, JiraRestAccess.MAP_WITH_LINKTYPES_TYPE_REFERENCE);
        return res.get("issueLinkTypes");
    }

    public void linkIssues(IssueLink issueLink) throws IOException, RestException {
        access.executePost("issueLink", issueLink);
    }

    public InputStream loadAttachment(RemoteAttachment attachment) throws IOException, RestException {
        Map<String, Object> info = (Map<String, Object>) access.executeGet("attachment/" + attachment.getId());
        String url = (String) info.get("content");
        GetMethod get = new GetMethod(url);
        int status = access.executeMethod(get);
        if (status != HttpStatus.SC_OK) {
            throw new IOException("Could not load attachment. Status: " + status + ", " + get.getStatusLine().getReasonPhrase());
        }
        return get.getResponseBodyAsStream();
    }

    public List<RemoteProject> getAllProjects() throws IOException, RestException {
        final List<Map<String, Object>> projects = (List<Map<String, Object>>) access.executeGet("project");
        List<RemoteProject> res = new ArrayList<RemoteProject>();
        for (Map<String, Object> project : projects) {
            res.add(new RemoteProject((String) project.get("id"), (String) project.get("name"), null, null,
                    (String) project.get("key"), null, null, null, null, null));
        }
        return res;
    }

    public List<RemoteProject> getProjectsByKey(String... keys) throws IOException, RestException {
        final List<RemoteProject> projects = getAllProjects();
        for (Iterator<RemoteProject> i = projects.iterator(); i.hasNext(); ) {
            RemoteProject project = i.next();
            boolean found = false;
            for (String key : keys) {
                if (project.getKey().equals(key)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                i.remove();
            }
        }
        return projects;
    }

    public List<Map<String, Object>> getAllIssuesByJql(String jql, String fields, String expand) throws IOException, RestException {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        int start = 0;
        List<Map<String, Object>> result;
        do {
            long a = System.currentTimeMillis();
            result = getIssuesByJql(jql, start, 500, fields, expand);
            long b = System.currentTimeMillis();
            System.out.println((b - a) /500);
            res.addAll(result);
            start += result.size();
        } while (!result.isEmpty());
        return res;
    }

    public List<Map<String, Object>> getIssuesByJql(String jql, int startAt, int maxResults, String fields, String expand) throws IOException, RestException {
        final HashMap<String, Object> req = new HashMap<String, Object>();
        req.put("jql", jql);
        req.put("startAt", startAt);
        req.put("maxResults", maxResults);
        if (fields != null) {
            req.put("fields", fields.split("\\s*,\\s*"));
        }
        if (expand != null) {
            req.put("expand", expand);
        }
        final Map<String, Object> res = (Map<String, Object>) access.executePost("search", req);
        return (List<Map<String, Object>>) res.get("issues");
    }
}