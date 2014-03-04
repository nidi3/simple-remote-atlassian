package guru.nidi.atlassian.remote.bamboo.rest;

import java.util.List;

/**
 *
 */
public class BambooResults {
    private List<BambooResult> result;

    public List<BambooResult> getResult() {
        return result;
    }

    public void setResult(List<BambooResult> result) {
        this.result = result;
    }
}
