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
package guru.nidi.atlassian.remote.jira.rest;

import com.atlassian.jira.rpc.soap.beans.*;
import guru.nidi.atlassian.remote.jira.RemoteIssueExt;
import guru.nidi.atlassian.remote.rest.RestException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

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
        HttpGet method = access.get("issueLinkType");
        HttpResponse response = access.executeMethod(method);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            Map<String, Object> errorMsg = access.readResponse(response, JiraRestAccess.MAP_TYPE_REFERENCE);
            throw new RestException(errorMsg);
        }

        Map<String, List<IssueLinkType>> res = access.readResponse(response, JiraRestAccess.MAP_WITH_LINKTYPES_TYPE_REFERENCE);
        return res.get("issueLinkTypes");
    }

    public void linkIssues(IssueLink issueLink) throws IOException, RestException {
        access.executePost("issueLink", issueLink);
    }

    public InputStream loadAttachment(RemoteAttachment attachment) throws IOException, RestException {
        Map<String, Object> info = (Map<String, Object>) access.executeGet("attachment/" + attachment.getId());
        String url = (String) info.get("content");
        HttpGet get = new HttpGet(url);
        HttpResponse response = access.executeMethod(get);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException("Could not load attachment. Status: " + response.getStatusLine().getStatusCode() + ", " + response.getStatusLine().getReasonPhrase());
        }
        return response.getEntity().getContent();
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

    public Map<String, Object> getIssue(String keyOrId, String fields, String expand) throws IOException, RestException {
        final Map<String, Object> params = stdQueryParams(new HashMap<String, Object>(), fields, expand);
        return (Map<String, Object>) access.executeGet("issue/" + keyOrId, params, Map.class);
    }

    private Map<String, Object> stdQueryParams(Map<String, Object> params, String fields, String expand) {
        if (fields != null) {
            params.put("fields", fields.split("\\s*,\\s*"));
        }
        if (expand != null) {
            params.put("expand", expand.split("\\s*,\\s*"));
        }
        return params;
    }

    public RemoteIssueExt getIssue(String keyOrId) throws IOException, RestException {
        return remoteIssueFromJson(getIssue(keyOrId, null, null));
    }

    public RemoteIssueExt[] getIssuesFromFilter(String filter) throws IOException, RestException {
        return getIssuesByJql("filter='" + filter + "'", 0, 500);
    }

    public List<Map<String, Object>> getAllIssuesByJql(String jql, String fields, String expand) throws IOException, RestException {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        int start = 0;
        List<Map<String, Object>> result;
        do {
            result = getIssuesByJql(jql, start, 500, fields, expand);
            res.addAll(result);
            start += result.size();
        } while (!result.isEmpty());
        return res;
    }

    public List<Map<String, Object>> getIssuesByJql(String jql, int startAt, int maxResults, String fields, String expand) throws IOException, RestException {
        final Map<String, Object> req = stdQueryParams(new HashMap<String, Object>(), fields, expand);
        req.put("jql", jql);
        req.put("startAt", startAt);
        req.put("maxResults", Math.min(maxResults, 500));
        final Map<String, Object> res = (Map<String, Object>) access.executePost("search", req);
        final List<Map<String, Object>> issues = (List<Map<String, Object>>) res.get("issues");
        final int read = startAt + issues.size();
        if (read < (Integer) res.get("total") && read < maxResults) {
            issues.addAll(getIssuesByJql(jql, read, maxResults, fields, expand));
        }
        return issues;
    }

    public RemoteIssueExt[] getIssuesByJql(String jql, int startAt, int maxResults) throws IOException, RestException {
        List<Map<String, Object>> issues = getIssuesByJql(jql, startAt, maxResults, null, null);
        RemoteIssueExt[] res = new RemoteIssueExt[issues.size()];
        int index = 0;
        for (Map<String, Object> issue : issues) {
            res[index] = remoteIssueFromJson(issue);
            index++;
        }
        return res;
    }

    public RemoteVersion[] getVersions(String projectKey) throws IOException, RestException {
        final List<Map<String, Object>> versions = (List<Map<String, Object>>) access.executeGet("project/" + projectKey + "/versions");
        return versions(versions);
    }

    private RemoteIssueExt remoteIssueFromJson(Map<String, Object> issue) {
        Map<String, Object> fields = (Map<String, Object>) issue.get("fields");
        return new RemoteIssueExt((String) issue.get("id"),
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
                longOf(((Map<String, Integer>) fields.get("votes")).get("votes")),
                (Integer) fields.get("aggregatetimeoriginalestimate"),
                (Integer) fields.get("timeoriginalestimate"),
                (Integer) fields.get("aggregatetimespent"),
                (Integer) fields.get("timespent"));
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
            components.add(new RemoteComponent(comp.get("id"), comp.get("name")));
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