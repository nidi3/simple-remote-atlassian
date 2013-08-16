package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stni.atlassian.remote.JiraService;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class DefaultQueryService implements QueryService {
    private final static Logger LOG = LoggerFactory.getLogger(DefaultQueryService.class);

    private final JiraService service;

    public DefaultQueryService(JiraService service) {
        this.service = service;
    }

    public List<RemoteProject> getProjectsByKey(String... keys) {
        if (keys.length == 0) {
            return service.getAllProjects();
        }
        if (keys.length == 1) {
            return Collections.singletonList(service.getProjectByKey(keys[0]));
        }
        return service.getProjectsByKey(keys);
    }

    public RemoteIssue getIssue(String issueKey) {
        return service.getIssue(issueKey);
    }

    @Override
    public RemoteIssue[] getIssuesFromFilter(String filter) {
        return service.getIssuesFromFilter(filter);
    }

    public RemoteIssue[] getIssuesFromJqlSearch(String query, int maxResults) {
        LOG.debug("Executing jql '{}'", query);
        return service.getIssuesFromJqlSearch(query, maxResults);
    }

    public String getBaseUrl() {
        return service.getBaseUrl();
    }

    public String getCustomField(RemoteIssue issue, String name) {
        return service.customFieldByName(issue, name);
    }

}
