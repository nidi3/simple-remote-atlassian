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
package guru.nidi.atlassian.remote.jira;

import com.atlassian.jira.rpc.soap.beans.*;
import guru.nidi.atlassian.remote.jira.rest.IssueLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.*;

/**
 *
 */
public class JiraTasks {
    private final static Logger LOG = LoggerFactory.getLogger(JiraTasks.class);

    private static final int MAX_ISSUES = 1000;
    private static final Comparator<RemoteIssue> ISSUE_BY_TYPE_COMPARATOR = new Comparator<RemoteIssue>() {
        public int compare(RemoteIssue o1, RemoteIssue o2) {
            return o1.getType().compareTo(o2.getType());
        }
    };
    private static final String CUSTOMFIELD = "customfield_";
    private static final RemoteResolution UNRESOLVED = new RemoteResolution(null, "Unresolved", "The issue is not resolved yet.", null);
    private final JiraService service;
    private Map<String, String> fieldNames;
    private Map<String, RemoteIssueType> issueTypeById, issueTypeByName;
    private Map<String, RemoteStatus> statusById, statusByName;
    private Map<String, RemotePriority> priorityById;
    private Map<String, RemoteResolution> resolutionById;

    public JiraTasks(JiraService service) {
        this.service = service;
    }

    public JiraService getService() {
        return service;
    }

    public RemoteIssueType issueTypeById(String id) throws java.rmi.RemoteException {
        if (issueTypeById == null) {
            issueTypeById = new HashMap<String, RemoteIssueType>();
            RemoteIssueType[] issueTypes = service.getIssueTypes();
            for (RemoteIssueType type : issueTypes) {
                issueTypeById.put(type.getId(), type);
            }
            RemoteIssueType[] issueSubTypes = service.getSubTaskIssueTypes();
            for (RemoteIssueType type : issueSubTypes) {
                issueTypeById.put(type.getId(), type);
            }
        }
        return id == null ? null : issueTypeById.get(id);
    }

    public RemoteIssueType issueTypeByName(String name) throws java.rmi.RemoteException {
        if (issueTypeByName == null) {
            issueTypeByName = new HashMap<String, RemoteIssueType>();
            RemoteIssueType[] issueTypes = service.getIssueTypes();
            for (RemoteIssueType type : issueTypes) {
                issueTypeByName.put(type.getName().toLowerCase(), type);
            }
            RemoteIssueType[] issueSubTypes = service.getSubTaskIssueTypes();
            for (RemoteIssueType type : issueSubTypes) {
                issueTypeByName.put(type.getName().toLowerCase(), type);
            }
        }
        return name == null ? null : issueTypeByName.get(name.toLowerCase());
    }

    public String fieldNameById(String fieldId) {
        if (fieldNames == null) {
            fieldNames = new HashMap<String, String>();
            List<Map<String, String>> fields = (List<Map<String, String>>) service.executeGet("field");
            for (Map<String, String> field : fields) {
                String id = field.get("id");
                if (id.startsWith(CUSTOMFIELD)) {
                    fieldNames.put(normalizedFieldId(id), field.get("name"));
                }
            }
        }
        return fieldNames.get(normalizedFieldId(fieldId));
    }

    private String normalizedFieldId(String fieldId) {
        if (fieldId.startsWith(CUSTOMFIELD)) {
            return fieldId.substring(CUSTOMFIELD.length());
        }
        return fieldId;
    }

    public String customFieldByName(RemoteIssue issue, String fieldName) {
        for (RemoteCustomFieldValue value : issue.getCustomFieldValues()) {
            if (fieldNameById(value.getCustomfieldId()).equalsIgnoreCase(fieldName)) {
                return value.getValues().length == 0 ? null : value.getValues()[0];
            }
        }
        LOG.debug("Custom field '{}' not found in issue {}", fieldName, issue.getKey());
        return null;
    }

    public RemoteVersion versionByName(RemoteProject project, String name) throws java.rmi.RemoteException {
        RemoteVersion[] versions = service.getVersions(project.getKey());
        for (RemoteVersion version : versions) {
            if (version.getName().equals(name)) {
                return version;
            }
        }
        return null;
    }

    public RemoteNamedObject actionByName(String issueKey, String name) {
        for (RemoteNamedObject action : service.getAvailableActions(issueKey)) {
            if (action.getName().equalsIgnoreCase(name) || action.getName().equalsIgnoreCase(name + " Issue")) {
                return action;
            }
        }
        return null;
    }

    public RemoteStatus statusByName(String name) {
        if (statusByName == null) {
            statusByName = new HashMap<String, RemoteStatus>();
            for (RemoteStatus status : service.getStatuses()) {
                statusByName.put(status.getName().toLowerCase(), status);
            }
        }
        return name == null ? null : statusByName.get(name.toLowerCase());
    }

    public RemoteStatus statusById(String id) {
        if (statusById == null) {
            statusById = new HashMap<String, RemoteStatus>();
            for (RemoteStatus status : service.getStatuses()) {
                statusById.put(status.getId(), status);
            }
        }
        return id == null ? null : statusById.get(id);
    }

    public RemotePriority priorityById(String id) {
        if (priorityById == null) {
            priorityById = new HashMap<String, RemotePriority>();
            for (RemotePriority priority : service.getPriorities()) {
                priorityById.put(priority.getId(), priority);
            }
        }
        return id == null ? null : priorityById.get(id);
    }

    public RemoteResolution resolutionById(String id) {
        if (resolutionById == null) {
            resolutionById = new HashMap<String, RemoteResolution>();
            for (RemoteResolution resolution : service.getResolutions()) {
                resolutionById.put(resolution.getId(), resolution);
            }
        }
        return id == null ? UNRESOLVED : resolutionById.get(id);
    }

    public long timeToResolve(RemoteIssue issue) {
        Calendar resolution = service.getResolutionDateByKey(issue.getKey());
        if (resolution == null) {
            return 0;
        }
        return resolution.getTimeInMillis() - issue.getCreated().getTimeInMillis();
    }

    public RemoteIssue progressStatusAction(String issueKey, String actionName, String statusName) {
        RemoteNamedObject action = actionByName(issueKey, actionName);
        if (action == null) {
            throw new IllegalArgumentException("Action with name '" + actionName + "' not found.");
        }
        RemoteStatus status = statusByName(statusName);
        if (status == null) {
            throw new IllegalArgumentException("Status with name '" + statusName + "' not found.");
        }
        RemoteFieldValue statusField = new RemoteFieldValue("status", new String[]{status.getId()});
        return service.progressWorkflowAction(issueKey, action.getId(), new RemoteFieldValue[]{statusField});
    }

    public RemoteSecurityLevel securityLevelByName(RemoteProject project, String name) {
        RemoteSecurityLevel[] levels = service.getSecurityLevels(project.getKey());
        for (RemoteSecurityLevel level : levels) {
            if (level.getName().equalsIgnoreCase(name)) {
                return level;
            }
        }
        return null;
    }

    public RemoteIssue[] getIssues(RemoteProject project, RemoteVersion fixVersion, boolean onlyFixed) throws java.rmi.RemoteException {
        String query = "project = " + project.getName() + " AND fixVersion = " + fixVersion.getId();
        if (onlyFixed) {
            query += " AND resolution=fixed";
        }
        return service.getIssuesFromJqlSearch(query, MAX_ISSUES);
    }

    public String makeReleaseNotes(RemoteProject project, RemoteVersion version, boolean onlyFixed) throws java.rmi.RemoteException {
        StringBuilder s = new StringBuilder();
        s.append("<h1>Release Notes - " + project.getName() + " - Version " + version.getName() + "</h1>\n");
        RemoteIssue[] issues = getIssues(project, version, onlyFixed);
        Arrays.sort(issues, ISSUE_BY_TYPE_COMPARATOR);
        String projectBaseUrl = project.getUrl().substring(0, project.getUrl().lastIndexOf('/') + 1);

        String type = null;
        for (RemoteIssue issue : issues) {
            if (!issue.getType().equals(type)) {
                if (type != null) {
                    s.append("</ul>");
                }
                type = issue.getType();
                s.append("\n<h2>" + issueTypeById(type).getName() + "</h2>\n");
                s.append("<ul>\n");
            }
            s.append("<li>[<a href='" + projectBaseUrl + issue.getKey() + "'>" + issue.getKey() + "</a>] - " + issue.getSummary() + "</li>\n");
        }
        s.append("</ul>");
        return s.toString();
    }

    public RemoteIssue createRequirementAndFeature(RemoteProject project, String summary, String description, String securityLevel) throws RemoteException {
        RemoteIssue req = new RemoteIssue();
        req.setProject(project.getKey());
        req.setType(issueTypeByName("requirement").getId());
        req.setSummary(summary);
        req.setDescription(description);
        req = service.createIssue(req);

        RemoteIssue feature = new RemoteIssue();
        feature.setProject(project.getKey());
        feature.setType(issueTypeByName("feature").getId());
        feature.setSummary(summary);
        if (securityLevel != null) {
            feature = service.createIssueWithSecurityLevel(feature, Long.parseLong(securityLevelByName(project, securityLevel).getId()));
        } else {
            feature = service.createIssue(feature);
        }
        service.linkIssues(new IssueLink("Cloners", req.getKey(), feature.getKey()));
        return req;
    }


}
