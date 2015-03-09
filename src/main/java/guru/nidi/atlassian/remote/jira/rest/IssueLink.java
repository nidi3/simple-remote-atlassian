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
package guru.nidi.atlassian.remote.jira.rest;

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
