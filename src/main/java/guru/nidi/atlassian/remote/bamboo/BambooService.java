package guru.nidi.atlassian.remote.bamboo;

import guru.nidi.atlassian.remote.bamboo.rest.BambooPlan;
import guru.nidi.atlassian.remote.bamboo.rest.BambooResult;
import guru.nidi.atlassian.remote.bamboo.rest.BambooServerInfo;

import java.util.List;

/**
 *
 */
public interface BambooService {
    BambooServerInfo getServerInfo();

    List<BambooPlan> getBuildPlans();

    List<BambooResult> getResults();

    BambooResultDetail getResultDetail(String key);
}
