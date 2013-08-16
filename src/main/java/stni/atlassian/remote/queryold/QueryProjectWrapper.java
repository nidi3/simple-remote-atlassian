package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteScheme;

/**
 *
 */
abstract class QueryProjectWrapper extends AbstractQueryObject<QueryProject>{
    private final RemoteProject project;

    protected QueryProjectWrapper(JiraQuery query, RemoteProject project) {
        super(query);
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QueryProjectWrapper that = (QueryProjectWrapper) o;

        if (project != null ? !project.equals(that.project) : that.project != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QueryProjectWrapper{" +
                "project=" + project +
                '}';
    }

    public RemoteProject getProject() {
        return project;
    }

    public String getDescription() {
        return project.getDescription();
    }

    public RemoteScheme getIssueSecurityScheme() {
        return project.getIssueSecurityScheme();
    }

    public String getKey() {
        return project.getKey();
    }

    public String getLead() {
        return project.getLead();
    }

    public RemoteScheme getNotificationScheme() {
        return project.getNotificationScheme();
    }

    public RemotePermissionScheme getPermissionScheme() {
        return project.getPermissionScheme();
    }

    public String getProjectUrl() {
        return project.getProjectUrl();
    }

    public String getUrl() {
        return project.getUrl();
    }

    public String getName() {
        return project.getName();
    }

    public String getId() {
        return project.getId();
    }
}
