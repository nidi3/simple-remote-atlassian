/*
 * Copyright (C) 2013 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
