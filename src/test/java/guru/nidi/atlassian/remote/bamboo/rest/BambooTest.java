package guru.nidi.atlassian.remote.bamboo.rest;

import guru.nidi.atlassian.remote.TestUtils;
import guru.nidi.atlassian.remote.bamboo.BambooResultDetail;
import guru.nidi.atlassian.remote.bamboo.BambooService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 *
 */
public class BambooTest {
    private BambooService service = TestUtils.bambooService();

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
