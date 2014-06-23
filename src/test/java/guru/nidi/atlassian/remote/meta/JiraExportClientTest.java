package guru.nidi.atlassian.remote.meta;

import guru.nidi.atlassian.remote.TestUtils;
import guru.nidi.atlassian.remote.meta.client.JiraExportClient;
import org.junit.Ignore;
import org.junit.Test;

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
        final JiraGenerateRequest request = TestUtils.jiraGenerateRequest();
        request.setTemplate("rn");
        request.setStyle("mima");
        request.setVersions("Sprint 4");
        request.setProjectKey("IPOM");
        final InputStream export = client.getExport(request);
        System.out.println(export);
    }
}
