package guru.nidi.atlassian.remote.meta;

import org.junit.Ignore;
import org.junit.Test;
import guru.nidi.atlassian.remote.meta.client.JiraExportClient;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class JiraExportClientTest {
    @Test
    @Ignore
    public void simple() throws IOException {
        final JiraExportClient client = new JiraExportClient("http://localhost:8080", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        final JiraGenerateRequest request = new JiraGenerateRequest();
        request.setUrl("https://jira.mimacom.com");
        request.setTemplate("rn");
        request.setStyle("mima");
        request.setVersions("Sprint 4");
        request.setProjectKey("IPOM");
        final InputStream export = client.getExport(request);
        System.out.println(export);
    }
}
