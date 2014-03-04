import com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry;
import com.atlassian.confluence.rpc.soap.beans.RemotePage;
import com.atlassian.confluence.rpc.soap.beans.RemotePageUpdateOptions;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;
import org.junit.Ignore;
import org.junit.Test;
import guru.nidi.atlassian.remote.confluence.ConfluenceService;
import guru.nidi.atlassian.remote.jira.JiraService;
import guru.nidi.atlassian.remote.script.RemoteJira;
import guru.nidi.atlassian.remote.confluence.ConfluenceTasks;
import guru.nidi.atlassian.remote.jira.JiraTasks;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class RemoteJiraTest {
    @Test
    @Ignore
    public void testExecute() throws Exception {
        RemoteJira jira = new RemoteJira("http://localhost:2990/jira", "admin", "admin");
        System.out.println(jira.getServerInfo());
        Object proj = ((List) jira.getProjectsNoSchemes()).get(0);
        System.out.println(proj);
        System.out.println(jira.getVersions((String) ((Map<String, Object>) proj).get("key")));
    }

    @Test
    @Ignore
    public void testSoap() throws Exception {
        JiraService js=new JiraService(System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        System.out.println(js.getServerInfo());
        RemoteProject sibad = js.getProjectByKey("SIBAD");



        ConfluenceService cs = new ConfluenceService(System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        ConfluenceTasks ct = new ConfluenceTasks(cs);

        RemotePage page = ct.getOrCreatePage(33721722, "www");
        Thread.sleep(2000);
        page.setContent("www");
        cs.updatePage(page, new RemotePageUpdateOptions());


        RemoteBlogEntry blog = new RemoteBlogEntry();
        blog.setContent("content");
        blog.setSpace("~stni");
        blog.setTitle("title23");
        //blog.se
        //blog = cs.storeBlogEntry(blog);
        JiraTasks jt=new JiraTasks(js);
//        RemotePage page = ct.getOrCreatePage(20317701, "Neuer Titel");
//        page.setContent(page.getContent() + "<br>next");
//        cs.updatePage(page, new RemotePageUpdateOptions());
        //RemotePage sub = cs.getPage(page.getSpace(), "Release 4.4.6");

        //RemoteField[] fields = service.getCustomFields(login);
        RemoteVersion version = jt.version(sibad, "Release 4.4");
        String releaseNotes = jt.makeReleaseNotes(sibad, version, false);
        //System.out.println(releaseNotes);
        //service.addVersion(login,sibad.getKey(),new RemoteVersion(null,"TestVersion",false,null,false,0L));
        //RemoteField[] customFields = service.getCustomFields(login);
        RemoteIssue issue = js.getIssue("SIBAD-295");
        //issue.getCustomFieldValues()
    }

}

