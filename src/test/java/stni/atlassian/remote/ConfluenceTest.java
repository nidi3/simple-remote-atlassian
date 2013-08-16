package stni.atlassian.remote;

import com.atlassian.confluence.rpc.soap.beans.RemotePage;
import com.atlassian.confluence.rpc.soap.beans.RemoteSpace;
import com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class ConfluenceTest {
    @Test
    @Ignore
    public void simple(){
        DefaultConfluenceService service = new DefaultConfluenceService("https://rune.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        RemoteSpaceSummary[] spaces = service.getSpaces();
        RemoteSpace moa = service.getSpace("LIVINGSERVICES");
        RemotePage page = service.getPage("LIVINGSERVICES", "bega-test");
        System.out.println(page.getContent());

        page = service.getPage("LIVINGSERVICES", "Einführung und Ziele");
        System.out.println(page.getContent());

        page = service.getPage("LIVINGSERVICES", "Projektaspekte");
        System.out.println(page.getContent());

        page = service.getPage("LIVINGSERVICES", "Randbedingungen");
        System.out.println(page.getContent());
    }
}
