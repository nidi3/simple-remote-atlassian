package stni.atlassian.remote.rest;

/**
 *
 */
public class IssueLink {
    private String linkType;
    private String fromIssueKey;
    private String toIssueKey;

    public IssueLink() {
    }

    public IssueLink(String linkType, String fromIssueKey, String toIssueKey) {
        this.linkType = linkType;
        this.fromIssueKey = fromIssueKey;
        this.toIssueKey = toIssueKey;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getFromIssueKey() {
        return fromIssueKey;
    }

    public void setFromIssueKey(String fromIssueKey) {
        this.fromIssueKey = fromIssueKey;
    }

    public String getToIssueKey() {
        return toIssueKey;
    }

    public void setToIssueKey(String toIssueKey) {
        this.toIssueKey = toIssueKey;
    }
}
