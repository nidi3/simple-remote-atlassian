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
package it.guru.nidi.atlassian.remote.jira;

import com.atlassian.jira.rpc.soap.beans.RemoteCustomFieldValue;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import com.atlassian.jira.rpc.soap.beans.RemoteVersion;
import guru.nidi.atlassian.remote.jira.DefaultJiraService;
import guru.nidi.atlassian.remote.jira.JiraTasks;
import it.guru.nidi.atlassian.remote.TestUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class JiraTasksLiveTest {
    @BeforeClass
    public static void init() {
        System.out.println("Start jira with 'mvn jira:run' or run as integration test.");
    }

    @Test
    @Ignore
    public void testCreateRequirementAndFeature() throws Exception {
        JiraTasks tasks = new JiraTasks(new DefaultJiraService("http://localhost:2990/jira", "admin", "admin"));
        RemoteVersion[] vs = tasks.getService().getVersions("TST");
        RemoteIssue i = tasks.getService().getIssue("MOA-63");
//        List<Map<String, Object>> allIssuesByJql = tasks.getService().getIssuesByJql("project in (LS) and (type in (Epic,'Non Functional Requirement') and fixVersion='r1.0' and level is empty and component is not empty)", 1, 2,null,null);
        RemoteIssue[] issues = tasks.getService().getIssuesByJql("project in (LS) and (type in (Epic,'Non Functional Requirement') and fixVersion='r1.0' and level is empty and component is not empty)", 1, 1);
        print(issues[0]);
        System.out.println("-------------------");
        RemoteIssue issue1 = tasks.getService().getIssue("LS-657");
        print(issue1);
        //RemoteIssue[] issues = tasks.getService().getIssuesFromJqlSearch("project in (LS) and (type in (Epic,'Non Functional Requirement') and fixVersion='r1.0' and level is empty and component is not empty)",3);
        tasks.progressStatusAction("IPOM-100", "close", "closed");
        tasks.progressStatusAction("IPOM-100", "resolve", "resolved");
        tasks.progressStatusAction("IPOM-100", "reopen", "reopened");

        RemoteIssue issue = tasks.getService().getIssue("IPOM-59");
        String s = tasks.customFieldByName(issue, "Test case description");
        String closeId = tasks.actionByName(issue.getKey(), "Close").getId();
        String closedId = tasks.statusByName("Closed").getId();
        //tasks.progressStatusAction("IPOM-200", "close", "closed");
        tasks.progressStatusAction("IPOM-200", "reopen", "reopened");

        String fieldName = tasks.fieldNameById("10110");
        String documentation = tasks.customFieldByName(issue, "Documentation");
        //RemoteAttachment[] attachments = tasks.getService().getAttachmentsFromIssue("SIBAD-566");
        RemoteProject sibad = tasks.getService().getProjectByKey("SIBAD");
        issue = tasks.getService().getIssue("IPOM-113");
        Object o = tasks.getService().executeGet("issue/IPOM-113");
        List linkTypes = tasks.getService().getIssueLinkTypes();
        tasks.createRequirementAndFeature(sibad, "My summary", "my desc", "mimacom security level");
    }

    @Test
    @Ignore
    public void testDelete() throws Exception {
        JiraTasks tasks = new JiraTasks(TestUtils.jiraService());
        for (int i = 1229; i <= 1234; i++) {
            //tasks.getService().deleteIssue("SIBAD-" + i);
        }
    }

    private void print(RemoteIssue issue) {
        Arrays.sort(issue.getCustomFieldValues(), new Comparator<RemoteCustomFieldValue>() {
            @Override
            public int compare(RemoteCustomFieldValue o1, RemoteCustomFieldValue o2) {
                return o1 == null ? 1 : o2 == null ? -1 : key(o1).compareTo(key(o2));
            }

            private String key(RemoteCustomFieldValue v) {
                return v.getCustomfieldId() == null ? "" : v.getCustomfieldId();
            }
        });
        for (RemoteCustomFieldValue value : issue.getCustomFieldValues()) {
            System.out.println(value == null ? null : (value.getCustomfieldId() + " " + value.getKey() + " " + Arrays.toString(value.getValues())));
        }
    }
}
