package stni.atlassian.remote.bamboo;

import stni.atlassian.remote.bamboo.rest.BambooPlan;
import stni.atlassian.remote.bamboo.rest.BambooResult;
import stni.atlassian.remote.bamboo.rest.BambooServerInfo;

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
