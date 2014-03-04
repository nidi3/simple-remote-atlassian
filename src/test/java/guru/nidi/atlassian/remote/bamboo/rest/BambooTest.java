package guru.nidi.atlassian.remote.bamboo.rest;

import org.junit.Ignore;
import org.junit.Test;
import guru.nidi.atlassian.remote.bamboo.BambooResultDetail;
import guru.nidi.atlassian.remote.bamboo.BambooService;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 *
 */
public class BambooTest {
    private BambooService service = new DefaultBambooService("http://bamboo.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));

    @Test
    public void serverInfo() {
        final BambooServerInfo info = service.getServerInfo();
        assertNotNull(info.getVersion());
    }

    @Test
    public void buildPlans() {
        final List<BambooPlan> buildPlans = service.getBuildPlans();
        assertFalse(buildPlans.isEmpty());
    }

    @Test
    public void results() {
        final List<BambooResult> results = service.getResults();
        assertFalse(results.isEmpty());
    }

    @Test
    @Ignore
    public void resultDetail() {
        final BambooResultDetail detail = service.getResultDetail("IPOM-NIGHTLY-221");
        assertNotNull(detail);
    }
}
