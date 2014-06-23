package guru.nidi.atlassian.remote.jira;

import com.atlassian.jira.rpc.soap.beans.RemoteCustomFieldValue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;
import guru.nidi.atlassian.remote.TestUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class JiraTasksLiveTest {
    private void print(RemoteIssue issue) {
        Arrays.sort(issue.getCustomFieldValues(), new Comparator<RemoteCustomFieldValue>() {
            @Override
            public int compare(RemoteCustomFieldValue o1, RemoteCustomFieldValue o2) {
                return o1 == null ? 1 : o2 == null ? -1 : key(o1).compareTo(key(o2));
            }

            private String key(RemoteCustomFieldValue v) {
                return v.getCustomfieldId() == null ? "" : v.getCustomfieldId();
            }
        });
        for (RemoteCustomFieldValue value : issue.getCustomFieldValues()) {
            System.out.println(value == null ? null : (value.getCustomfieldId() + " " + value.getKey() + " " + Arrays.toString(value.getValues())));
        }
    }

    @Test
    @Ignore
    public void testCreateRequirementAndFeature() throws Exception {
        JiraTasks tasks = new JiraTasks(new DefaultJiraService("https://jira.atlassian.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS")));
        RemoteVersion[] vs = tasks.getService().getVersions("MOA");
        RemoteIssue i = tasks.getService().getIssue("MOA-63");
//        List<Map<String, Object>> allIssuesByJql = tasks.getService().getIssuesByJql("project in (LS) and (type in (Epic,'Non Functional Requirement') and fixVersion='r1.0' and level is empty and component is not empty)", 1, 2,null,null);
        RemoteIssue[] issues = tasks.getService().getIssuesByJql("project in (LS) and (type in (Epic,'Non Functional Requirement') and fixVersion='r1.0' and level is empty and component is not empty)", 1, 1);
        print(issues[0]);
        System.out.println("-------------------");
        RemoteIssue issue1 = tasks.getService().getIssue("LS-657");
        print(issue1);
        //RemoteIssue[] issues = tasks.getService().getIssuesFromJqlSearch("project in (LS) and (type in (Epic,'Non Functional Requirement') and fixVersion='r1.0' and level is empty and component is not empty)",3);
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
        JiraTasks tasks = new JiraTasks(TestUtils.jiraService());
        for (int i = 1229; i <= 1234; i++) {
            //tasks.getService().deleteIssue("SIBAD-" + i);
        }
    }
}
