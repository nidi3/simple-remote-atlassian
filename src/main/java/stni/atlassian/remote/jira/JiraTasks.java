package stni.atlassian.remote.jira;

import com.atlassian.jira.rpc.soap.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stni.atlassian.remote.jira.rest.IssueLink;

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
    private final JiraService service;
    private Map<String, String> fieldNames;

    public JiraTasks(JiraService service) {
        this.service = service;
    }

    public JiraService getService() {
        return service;
    }

    public RemoteIssueType issueTypeById(String id) throws java.rmi.RemoteException {
        RemoteIssueType[] issueTypes = service.getIssueTypes();
        for (RemoteIssueType type : issueTypes) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        RemoteIssueType[] issueSubTypes = service.getSubTaskIssueTypes();
        for (RemoteIssueType type : issueSubTypes) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }

    public RemoteIssueType issueTypeByName(String name) throws java.rmi.RemoteException {
        RemoteIssueType[] issueTypes = service.getIssueTypes();
        for (RemoteIssueType type : issueTypes) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        RemoteIssueType[] issueSubTypes = service.getSubTaskIssueTypes();
        for (RemoteIssueType type : issueSubTypes) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
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
        for (RemoteStatus status : service.getStatuses()) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }

    public RemoteStatus statusById(String id) {
        for (RemoteStatus status : service.getStatuses()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        return null;
    }

    public RemotePriority priorityById(String id) {
        for (RemotePriority priority : service.getPriorities()) {
            if (priority.getId().equals(id)) {
                return priority;
            }
        }
        return null;
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
