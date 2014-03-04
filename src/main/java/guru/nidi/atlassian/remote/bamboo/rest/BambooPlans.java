package guru.nidi.atlassian.remote.bamboo.rest;

import java.util.List;

/**
 *
 */
public class BambooPlans {
    private List<BambooPlan> plan;

    public List<BambooPlan> getPlan() {
        return plan;
    }

    public void setPlan(List<BambooPlan> plan) {
        this.plan = plan;
    }
}
