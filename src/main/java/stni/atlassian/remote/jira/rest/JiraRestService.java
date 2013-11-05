package stni.atlassian.remote.jira.rest;

import com.atlassian.jira.rpc.soap.beans.*;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import stni.atlassian.remote.rest.RestException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<Map<String, Object>> getAllIssuesByJql(String jql, String fields, List<String> expand) throws IOException, RestException {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        int start = 0;
        List<Map<String, Object>> result;
        do {
            long a = System.currentTimeMillis();
            result = getIssuesByJql(jql, start, 500, fields, expand);
            long b = System.currentTimeMillis();
            System.out.println((b - a) / 500);
            res.addAll(result);
            start += result.size();
        } while (!result.isEmpty());
        return res;
    }

    public List<Map<String, Object>> getIssuesByJql(String jql, int startAt, int maxResults, String fields, List<String> expand) throws IOException, RestException {
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

    public RemoteIssue[] getIssuesByJql(String jql, int startAt, int maxResults) throws IOException, RestException {
        List<Map<String, Object>> issues = getIssuesByJql(jql, startAt, maxResults, null, null);
        RemoteIssue[] res = new RemoteIssue[issues.size()];
        int index = 0;
        for (Map<String, Object> issue : issues) {
            Map<String, Object> fields = (Map<String, Object>) issue.get("fields");
            res[index] = new RemoteIssue((String) issue.get("id"),
                    versions(access(fields, "versions")),
                    access(fields, "assignee", "name"),
                    null, components(fields),
                    calendar((String) fields.get("created")),
                    customFields(fields),
                    (String) fields.get("description"),
                    calendar((String) fields.get("duedate")),
                    (String) fields.get("environment"),
                    versions(access(fields, "fixVersions")),
                    (String) issue.get("key"),
                    access(fields, "priority", "id"),
                    access(fields, "project", "key"),
                    access(fields, "reporter", "name"),
                    access(fields, "resolution", "id"),
                    access(fields, "status", "id"),
                    (String) fields.get("summary"),
                    access(fields, "issuetype", "id"),
                    calendar((String) fields.get("updated")),
                    longOf(((Map<String, Integer>) fields.get("votes")).get("votes")));
            index++;
        }
        return res;
    }

    private String access(Map<String, Object> fields, String key, String sub) {
        Map<String, String> map = (Map<String, String>) fields.get(key);
        return map == null ? null : map.get(sub);
    }

    private List<Map<String, Object>> access(Map<String, Object> fields, String key) {
        return (List<Map<String, Object>>) fields.get(key);
    }

    private RemoteVersion[] versions(List<Map<String, Object>> versions) {
        List<RemoteVersion> vs = new ArrayList<RemoteVersion>();
        for (Map<String, Object> version : versions) {
            vs.add(new RemoteVersion((String) version.get("id"), (String) version.get("name"), (Boolean) version.get("archived"), calendar((String) version.get("releaseDate")), (Boolean) version.get("released"), null));
        }
        return vs.toArray(new RemoteVersion[vs.size()]);
    }

    private RemoteComponent[] components(Map<String, Object> fields) {
        List<RemoteComponent> components = new ArrayList<RemoteComponent>();
        for (Map<String, String> comp : (List<Map<String, String>>) fields.get("components")) {
            components.add(new RemoteComponent((String) comp.get("id"), (String) comp.get("name")));
        }
        return components.toArray(new RemoteComponent[components.size()]);
    }

    private RemoteCustomFieldValue[] customFields(Map<String, Object> fields) {
        List<RemoteCustomFieldValue> customFieldValues = new ArrayList<RemoteCustomFieldValue>();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            if (entry.getKey().startsWith("customfield_")) {
                if (entry.getValue() != null) {
                    customFieldValues.add(createCustomField(entry));
                }
            }
        }
        return customFieldValues.toArray(new RemoteCustomFieldValue[customFieldValues.size()]);
    }

    private RemoteCustomFieldValue createCustomField(Map.Entry<String, Object> entry) {
        if (entry.getValue() instanceof Map) {
            Map<String, String> v = (Map<String, String>) entry.getValue();
            Object value = v.get("value");
            return new RemoteCustomFieldValue(entry.getKey(), v.get("id"), (value instanceof String[]) ? (String[]) value : new String[]{(String) value});
        }
        return new RemoteCustomFieldValue(entry.getKey(), null, new String[]{entry.getValue().toString()});
    }

    private Calendar calendar(String value) {
        if (value == null) {
            return null;
        }
        try {
            DateFormat format = (value.length() == 10)
                    ? new SimpleDateFormat("yyyy-MM-dd")
                    : new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = format.parse(value);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            return null;
        }
    }

    private Long longOf(Integer value) {
        return value == null ? null : Long.valueOf(value);
    }
}