package guru.nidi.atlassian.remote;

import guru.nidi.atlassian.remote.bamboo.BambooService;
import guru.nidi.atlassian.remote.bamboo.rest.DefaultBambooService;
import guru.nidi.atlassian.remote.confluence.ConfluenceService;
import guru.nidi.atlassian.remote.confluence.DefaultConfluenceService;
import guru.nidi.atlassian.remote.jira.DefaultJiraService;
import guru.nidi.atlassian.remote.jira.JiraService;
import guru.nidi.atlassian.remote.jira.rest.JiraRestService;
import guru.nidi.atlassian.remote.meta.JiraGenerateRequest;

/**
 *
 */
public class TestUtils {

    private static final String USERNAME = System.getenv("JIRA_USER");
    private static final String PASSWORD = System.getenv("JIRA_PASS");
    private static final String BAMBOO_URL = System.getenv("BAMBOO_URL");
    private static final String CONFLUENCE_URL = System.getenv("CONFLUENCE_URL");
    private static final String JIRA_URL = System.getenv("JIRA_URL");

    public static BambooService bambooService() {
        return new DefaultBambooService(BAMBOO_URL, USERNAME, PASSWORD);
    }

    public static ConfluenceService confluenceService() {
        return new DefaultConfluenceService(CONFLUENCE_URL, USERNAME, PASSWORD);
    }

    public static JiraService jiraService() {
        return new DefaultJiraService(JIRA_URL, USERNAME, PASSWORD);
    }

    public static JiraRestService jiraRestService() {
        return new JiraRestService(JIRA_URL, USERNAME, PASSWORD);
    }

    public static JiraGenerateRequest jiraGenerateRequest() {
        final JiraGenerateRequest request = new JiraGenerateRequest();
        request.setUrl(JIRA_URL);
        return request;
    }
}
