package stni.atlassian.remote.jira;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class JiraTasksLiveTest {
    @Test
    @Ignore
    public void testCreateRequirementAndFeature() throws Exception {
        JiraTasks tasks = new JiraTasks(new DefaultJiraService("https://jira.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS")));
        RemoteIssue[] issues = tasks.getService().getIssuesFromJqlSearch("parent in (LS-354) and (type='Acceptance Criteria')",3);
        tasks.progressStatusAction("IPOM-100", "close", "closed");
        tasks.progressStatusAction("IPOM-100", "resolve", "resolved");
        tasks.progressStatusAction("IPOM-100", "reopen", "reopened");

        RemoteIssue issue = tasks.getService().getIssue("IPOM-59");
        String s = tasks.customFieldByName(issue, "Test case description");
        String closeId = tasks.actionByName(issue.getKey(), "Close").getId();
        String closedId = tasks.statusByName("Closed").getId();
        //tasks.progressStatusAction("IPOM-200", "close", "closed");
        tasks.progressStatusAction("IPOM-200", "reopen", "reopened");

        String fieldName = tasks.fieldNameById("10110");
        String documentation = tasks.customFieldByName(issue, "Documentation");
        //RemoteAttachment[] attachments = tasks.getService().getAttachmentsFromIssue("SIBAD-566");
        RemoteProject sibad = tasks.getService().getProjectByKey("SIBAD");
        issue = tasks.getService().getIssue("IPOM-113");
        Object o = tasks.getService().executeGet("issue/IPOM-113");
        List linkTypes = tasks.getService().getIssueLinkTypes();
        tasks.createRequirementAndFeature(sibad, "My summary", "my desc", "mimacom security level");
    }

    @Test
    @Ignore
    public void testDelete() throws Exception {
        JiraTasks tasks = new JiraTasks(new DefaultJiraService("https://jira.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS")));
        for (int i = 1229; i <= 1234; i++) {
            tasks.getService().deleteIssue("SIBAD-" + i);
        }
    }
}
