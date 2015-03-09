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
package guru.nidi.atlassian.remote.jira;

import com.atlassian.jira.rpc.soap.beans.RemoteComponent;
import com.atlassian.jira.rpc.soap.beans.RemoteCustomFieldValue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;

import java.util.Calendar;

/**
 *
 */
public class RemoteIssueExt extends RemoteIssue {
    private Integer aggregateTimeOriginalEstimate;
    private Integer timeOriginalEstimate;
    private Integer aggregateTimeSpent;
    private Integer timeSpent;

    public RemoteIssueExt() {
    }

    public RemoteIssueExt(String id, RemoteVersion[] affectsVersions, String assignee, String[] attachmentNames, RemoteComponent[] components, Calendar created, RemoteCustomFieldValue[] customFieldValues, String description, Calendar duedate, String environment, RemoteVersion[] fixVersions, String key, String priority, String project, String reporter, String resolution, String status, String summary, String type, Calendar updated, Long votes, Integer aggregateTimeOriginalEstimate, Integer timeOriginalEstimate, Integer aggregateTimeSpent, Integer timeSpent) {
        super(id, affectsVersions, assignee, attachmentNames, components, created, customFieldValues, description, duedate, environment, fixVersions, key, priority, project, reporter, resolution, status, summary, type, updated, votes);
        this.aggregateTimeOriginalEstimate = aggregateTimeOriginalEstimate;
        this.timeOriginalEstimate = timeOriginalEstimate;
        this.aggregateTimeSpent = aggregateTimeSpent;
        this.timeSpent = timeSpent;
    }

    public Integer getAggregateTimeOriginalEstimate() {
        return aggregateTimeOriginalEstimate;
    }

    public Integer getTimeOriginalEstimate() {
        return timeOriginalEstimate;
    }

    public Integer getAggregateTimeSpent() {
        return aggregateTimeSpent;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }
}
