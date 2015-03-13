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
package it.guru.nidi.atlassian.remote;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssueType;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;
import guru.nidi.atlassian.remote.bamboo.BambooService;
import guru.nidi.atlassian.remote.bamboo.rest.DefaultBambooService;
import guru.nidi.atlassian.remote.confluence.ConfluenceService;
import guru.nidi.atlassian.remote.confluence.DefaultConfluenceService;
import guru.nidi.atlassian.remote.jira.DefaultJiraService;
import guru.nidi.atlassian.remote.jira.JiraService;
import guru.nidi.atlassian.remote.jira.rest.JiraRestService;
import guru.nidi.atlassian.remote.meta.JiraGenerateRequest;

import java.util.Calendar;
import java.util.List;

/**
 *
 */
public class TestUtils {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BAMBOO_URL = "http://localhost:6990/bamboo";
    private static final String CONFLUENCE_URL = "http://localhost:1990/confluence";
    private static final String JIRA_URL = "http://localhost:2990/jira";

    public static BambooService bambooService() {
        return new DefaultBambooService(BAMBOO_URL, USERNAME, PASSWORD);
    }

    public static ConfluenceService confluenceService() {
        return new DefaultConfluenceService(CONFLUENCE_URL, USERNAME, PASSWORD);
    }

    public static JiraService jiraService() {
        return new DefaultJiraService(JIRA_URL, USERNAME, PASSWORD);
    }

    public static JiraRestService jiraRestService() {
        return new JiraRestService(JIRA_URL, USERNAME, PASSWORD);
    }

    public static JiraGenerateRequest jiraGenerateRequest() {
        final JiraGenerateRequest request = new JiraGenerateRequest();
        request.setUrl(JIRA_URL);
        return request;
    }

    public static void setupJira(JiraService js) {
        final RemoteProject tst;
        List<RemoteProject> list = js.getProjectsByKey("TST");
        if (list.isEmpty()) {
            tst = js.createProject("TST", "Test", "Description", "http://localhost:2990/jira/browse/TST", "admin", null, null, null);
            final RemoteIssueType[] issueTypes = js.getIssueTypes();
            js.createIssue(new RemoteIssue(null, null, "admin", null, null, Calendar.getInstance(), null, "Desc", null, null, null, "TST-1", null, "TST", "admin", null, null, "sum", issueTypes[0].getId(), null, 0L));
            js.addVersion("TST", new RemoteVersion(null, "First", false, Calendar.getInstance(), false, 0L));
        } else {
            tst = list.get(0);
        }
    }
}
