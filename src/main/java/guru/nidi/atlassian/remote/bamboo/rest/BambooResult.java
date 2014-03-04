package guru.nidi.atlassian.remote.bamboo.rest;

/**
 *
 */
public class BambooResult {
    private ResultLifecycleState lifeCycleState;
    private long id;
    private String key;
    private ResultState state;
    private int number;
    private BambooPlan plan;

    public ResultLifecycleState getLifeCycleState() {
        return lifeCycleState;
    }

    public void setLifeCycleState(ResultLifecycleState lifeCycleState) {
        this.lifeCycleState = lifeCycleState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ResultState getState() {
        return state;
    }

    public void setState(ResultState state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BambooPlan getPlan() {
        return plan;
    }

    public void setPlan(BambooPlan plan) {
        this.plan = plan;
    }
}
