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
package guru.nidi.atlassian.remote;

import guru.nidi.atlassian.remote.bamboo.BambooService;
import guru.nidi.atlassian.remote.bamboo.rest.DefaultBambooService;
import guru.nidi.atlassian.remote.confluence.ConfluenceService;
import guru.nidi.atlassian.remote.confluence.DefaultConfluenceService;
import guru.nidi.atlassian.remote.jira.DefaultJiraService;
import guru.nidi.atlassian.remote.jira.JiraService;
import guru.nidi.atlassian.remote.jira.rest.JiraRestService;
import guru.nidi.atlassian.remote.meta.JiraGenerateRequest;

/**
 *
 */
public class TestUtils {

    private static final String USERNAME = System.getenv("JIRA_USER");
    private static final String PASSWORD = System.getenv("JIRA_PASS");
    private static final String BAMBOO_URL = System.getenv("BAMBOO_URL");
    private static final String CONFLUENCE_URL = System.getenv("CONFLUENCE_URL");
    private static final String JIRA_URL = System.getenv("JIRA_URL");

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
}
