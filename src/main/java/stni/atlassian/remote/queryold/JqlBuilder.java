package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;

import java.util.List;

/**
 *
 */
final class JqlBuilder {
    private JqlBuilder() {
    }

    static String and(String expression) {
        StringBuilder s = new StringBuilder();
        if (!empty(expression)) {
            s.append(" and (").append(expression).append(")");
        }
        return s.toString();
    }

    static String orderBy(String expression) {
        StringBuilder s = new StringBuilder();
        if (!empty(expression)) {
            s.append(" order by ").append(expression);
        }
        return s.toString();
    }

    static String projectEq(RemoteProject project) {
        return project == null ? null : "project=" + project.getKey();
    }

    static String parentEq(RemoteIssue issue) {
        return "parent=" + issue.getKey();
    }

    static String parentIn(List<QueryIssue> issues) {
        StringBuilder s = new StringBuilder("parent in (");
        for (QueryIssue issue : issues) {
            s.append(issue.getKey()).append(",");
        }
        if (!issues.isEmpty()) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.append(")").toString();
    }

    static String projectIn(List<QueryProject> projects) {
        StringBuilder s = new StringBuilder("project in (");
        for (QueryProject project : projects) {
            s.append(project.getKey()).append(",");
        }
        if (!projects.isEmpty()) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.append(")").toString();
    }

    static String linkedWith(String issueKey, String linkType) {
        String res = "id in linkedissues(" + issueKey;
        if (linkType != null) {
            res += ",'" + linkType + "'";
        }
        return res + ")";
    }

    static String jql(String context, String query, String order) {
        if (context == null) {
            return query + orderBy(order);
        }
        return context + and(query) + orderBy(order);
    }

    private static boolean empty(String s) {
        return s == null || s.length() == 0;
    }
}
