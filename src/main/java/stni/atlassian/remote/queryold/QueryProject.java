package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteProject;

import java.util.List;

import static stni.atlassian.remote.queryold.JqlBuilder.projectEq;

/**
 *
 */
public class QueryProject extends QueryProjectWrapper implements QueryObject<QueryProject> {
    private final RemoteProject project;

    QueryProject(JiraQuery query, RemoteProject project) {
        super(query, project);
        this.project = project;
    }

    public QueryList<QueryIssue> children(String expression) {
        return children(expression, null);
    }

    public QueryList<QueryIssue> children(String expression, String order) {
        return QueryIssueList.ofJql(getQuery(), projectEq(getProject()), expression, order);
    }

    @Override
    public QueryList<QueryProject> typedLinked(String linkType, String expression, String order) {
        return newQueryList(getQuery(), null);
    }

    @Override
    public QueryList<QueryProject> newQueryList(JiraQuery query, List<QueryProject> objects) {
        return newQueryList(query, objects);
    }

    @Override
    public Object getField(String field) {
        return Util.getField(getProject(), field);
    }
}
