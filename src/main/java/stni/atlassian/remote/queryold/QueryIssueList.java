package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static stni.atlassian.remote.queryold.JqlBuilder.*;

/**
 *
 */
final class QueryIssueList extends AbstractQueryList<QueryIssue> {
    QueryIssueList(JiraQuery query, List<QueryIssue> issues) {
        super(query,issues);
    }

    public QueryList<QueryIssue> newQueryList(JiraQuery query, List<QueryIssue> issues) {
        return new QueryIssueList(query, issues);
    }

    static QueryList<QueryIssue> ofRemoteIssues(JiraQuery query, RemoteIssue... issues) {
        return ofRemoteIssueList(query, Arrays.asList(issues));
    }

    static QueryList<QueryIssue> ofRemoteIssueList(JiraQuery query, List<RemoteIssue> issues) {
        List<QueryIssue> list = new ArrayList<QueryIssue>(issues.size());
        for (RemoteIssue issue : issues) {
            list.add(new QueryIssue(query, issue));
        }
        return new QueryIssueList(query, list);
    }

    static QueryList<QueryIssue> ofJql(JiraQuery query, String context, String expression, String order) {
        return ofRemoteIssues(query, query.executeJql(jql(context, expression, order)));
    }

    public QueryList<QueryIssue> children(String expression, String order) {
        if (getElements().isEmpty()) {
            return newQueryList(getQuery(), null);
        }
        return ofJql(getQuery(), parentIn(getElements()), expression, order);
    }


    public QueryList<QueryIssue> typedLinked(String linkType, String expression, String order) {
        List<RemoteIssue> res = new ArrayList<RemoteIssue>();
        for (QueryIssue issue : getElements()) {
            res.addAll(Arrays.asList(getQuery().executeJql(jql(linkedWith(issue.getKey(), linkType), expression, order))));
        }
        return ofRemoteIssueList(getQuery(), res);
    }
}
