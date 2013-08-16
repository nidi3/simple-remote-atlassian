package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteComponent;
import com.atlassian.jira.rpc.soap.beans.RemoteCustomFieldValue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;

import java.util.Calendar;

/**
 *
 */
abstract class QueryIssueWrapper extends AbstractQueryObject<QueryIssue> {
    private final RemoteIssue issue;

    QueryIssueWrapper(JiraQuery query, RemoteIssue issue) {
        super(query);
        this.issue = issue;
    }

    public RemoteIssue getIssue() {
        return issue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QueryIssueWrapper that = (QueryIssueWrapper) o;
        if (!issue.equals(that.issue)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + issue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "QueryIssueWrapper{" +
                "issue=" + issue +
                '}';
    }

    public RemoteVersion[] getAffectsVersions() {
        return issue.getAffectsVersions();
    }

    public Calendar getCreated() {
        return issue.getCreated();
    }

    public String getStatus() {
        return issue.getStatus();
    }

    public Calendar getDuedate() {
        return issue.getDuedate();
    }

    public RemoteCustomFieldValue[] getCustomFieldValues() {
        return issue.getCustomFieldValues();
    }

    public RemoteComponent[] getComponents() {
        return issue.getComponents();
    }

    public String getEnvironment() {
        return issue.getEnvironment();
    }

    public String getType() {
        return issue.getType();
    }

    public String getAssignee() {
        return issue.getAssignee();
    }

    public String getResolution() {
        return issue.getResolution();
    }

    public String getPriority() {
        return issue.getPriority();
    }

    public String[] getAttachmentNames() {
        return issue.getAttachmentNames();
    }

    public RemoteVersion[] getFixVersions() {
        return issue.getFixVersions();
    }

    public Calendar getUpdated() {
        return issue.getUpdated();
    }

    public String getReporter() {
        return issue.getReporter();
    }

    public String getId() {
        return issue.getId();
    }

    public String getSummary() {
        return issue.getSummary();
    }

    public Long getVotes() {
        return issue.getVotes();
    }

    public String getProject() {
        return issue.getProject();
    }

    public String getDescription() {
        return issue.getDescription();
    }

    public String getKey() {
        return issue.getKey();
    }
}