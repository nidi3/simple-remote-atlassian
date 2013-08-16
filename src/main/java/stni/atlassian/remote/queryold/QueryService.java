package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;

import java.util.List;

/**
 *
 */
public interface QueryService {
    List<RemoteProject> getProjectsByKey(String... keys);

    RemoteIssue getIssue(String issueKey);

    RemoteIssue[] getIssuesFromFilter(String filter);

    RemoteIssue[] getIssuesFromJqlSearch(String query, int maxResults);

    String getBaseUrl();

    String getCustomField(RemoteIssue issue, String name);
}
