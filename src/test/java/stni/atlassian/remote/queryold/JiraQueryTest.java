package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssueType;
import org.junit.Ignore;
import org.junit.Test;
import stni.atlassian.remote.DefaultJiraService;
import stni.atlassian.remote.JiraService;

/**
 *
 */
public class JiraQueryTest {
    @Test
    @Ignore
    public void simple() {
        JiraQuery jq = JiraQuery.builder(new DefaultJiraService("https://jira.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS")))
                .maxResult(20)
                .build();
        JiraQuery jq2 = JiraQuery.builder(new DefaultJiraService("https://jira.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS")))
                .maxResult(20)
                .globalExpression("level is empty")
                .build();
        query(jq);
        query(jq2);
    }

    private void query(JiraQuery jq) {
        RemoteIssue[] remoteIssues = jq.executeJql("id in linkedissues(IPOM-52) and (type=\"Test Case\")");
        remoteIssues = jq.executeJql("id in linkedissues(IPOM-53) and (type=\"Test Case\")");

        QueryList<QueryIssue> reqs = jq.project("SIBAD").children("type=Requirement");
        System.out.println("reqs: " + reqs.size());
        QueryList<QueryIssue> features = reqs.linked("type=Feature");
        System.out.println("features: " + features.size());
        QueryList<QueryIssue> clonedFeatures = reqs.typedLinked("is cloned by", "type=Feature");
        System.out.println("cloned fs: " + clonedFeatures.size());
        QueryList<QueryIssue> subFeatures = features.children("");
        System.out.println("sub features: " + subFeatures.size());

        QueryList openBugs = jq.issue("SIBAD-1006").linked("type=Bug and status=open");
        System.out.println("open bugs: " + openBugs.size());
    }

    @Test
    @Ignore
    public void simple2() {
        final JiraService service = new DefaultJiraService("https://jira.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        JiraQuery jq = JiraQuery.builder(service)
                .maxResult(200)
                .build();

        final QueryList<QueryIssue> ipom2 = jq.project("IPOM").children("fixVersion=\"Sprint 3\" and level is empty and status in (Resolved, Closed) and resolution = Fixed");
        for (QueryIssue issue : ipom2) {
            System.out.println(issue.getKey() + ": "+issue.getType() +" "+ service.timeToResolve(issue.getIssue()));
        }

        final RemoteIssueType[] issueTypes = service.getIssueTypes();
        final QueryList<QueryProject> all1 = jq.allProjects();
        final QueryProject ipom1 = jq.project("IPOM");
        final QueryList<QueryProject> projects = jq.projects("IPOM", "SIBAD");
        final QueryProject global = jq.global();
        final QueryList<QueryIssue> children = global.children("type='Unforced Error'");

        QueryProject ipom = jq.project("IPOM");
        QueryList<QueryIssue> stories = ipom.children("type='User Story'", "label summary asc");
        QueryList<QueryIssue> allReqs = ipom.children("type=Requirement");
        QueryList<QueryIssue> linkedReqs = stories.linked("type=Requirement");
        QueryList<QueryIssue> generalReqs = allReqs.difference(linkedReqs);
        QueryList<QueryIssue> twice = allReqs.union(allReqs);
        QueryList<QueryIssue> all = twice.unique();
        for (QueryIssue story : stories) {
            System.out.println("story: " + story.getSummary());
            QueryList<QueryIssue> reqs = story.linked("type=Requirement");
            for (QueryIssue req : reqs) {
                System.out.println("req: " + req.getSummary());
            }
        }
    }

}
