package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteProject;

import java.util.ArrayList;
import java.util.List;

import static stni.atlassian.remote.queryold.JqlBuilder.projectIn;

/**
 *
 */
final class QueryProjectList extends AbstractQueryList<QueryProject> {
    private QueryProjectList(JiraQuery query, List<QueryProject> projects) {
        super(query,projects);
    }

    public QueryList<QueryProject> newQueryList(JiraQuery query, List<QueryProject> projects) {
        return new QueryProjectList(query, projects);
    }

    static QueryList<QueryProject> ofRemoteProjectList(JiraQuery query, List<RemoteProject> projects) {
        List<QueryProject> list = new ArrayList<QueryProject>(projects.size());
        for (RemoteProject project : projects) {
            list.add(new QueryProject(query, project));
        }
        return new QueryProjectList(query, list);
    }

    public QueryList<QueryIssue> children(String expression, String order) {
        if (getElements().isEmpty()) {
            return QueryIssueList.ofRemoteIssues(getQuery());
        }
        return QueryIssueList.ofJql(getQuery(), projectIn(getElements()), expression, order);
    }

    public QueryList<QueryProject> typedLinked(String linkType, String expression, String order) {
        return newQueryList(getQuery(), null);
    }

}
