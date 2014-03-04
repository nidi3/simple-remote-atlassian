package guru.nidi.atlassian.remote.meta;


/**
 *
 */
public class JiraGenerateRequest extends GenerateRequest {
    private String securityLevel;
    private boolean historyEntry;
    private String changeType;
    private String responsible;
    private String comment;
    private IssueFormat issueFormat;

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public boolean isHistoryEntry() {
        return historyEntry;
    }

    public void setHistoryEntry(boolean historyEntry) {
        this.historyEntry = historyEntry;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public IssueFormat getIssueFormat() {
        return issueFormat;
    }

    public void setIssueFormat(IssueFormat issueFormat) {
        this.issueFormat = issueFormat;
    }
}
