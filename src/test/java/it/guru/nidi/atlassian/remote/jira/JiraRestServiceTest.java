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

import com.atlassian.jira.rpc.soap.beans.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.atlassian.remote.jira.JiraService;
import guru.nidi.atlassian.remote.jira.JiraTasks;
import guru.nidi.atlassian.remote.jira.RemoteIssueExt;
import guru.nidi.atlassian.remote.jira.rest.IssueLink;
import guru.nidi.atlassian.remote.jira.rest.IssueLinkType;
import guru.nidi.atlassian.remote.jira.rest.JiraRestService;
import guru.nidi.atlassian.remote.rest.RestException;
import it.guru.nidi.atlassian.remote.TestUtils;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JiraRestServiceTest {
    private static JiraService js;

    @BeforeClass
    public static void init() {
        System.out.println("Start jira with 'mvn jira:run' or run as integration test.");
        js = TestUtils.jiraService();
        TestUtils.setupJira(js);
    }

    @Test
    @Ignore
    public void test() throws IOException, RestException {
        final RemoteIssueExt[] issuesByJql = js.getIssuesByJql("project=CWSEP and key=CWSEP-1346", 0, 100);
        final RemoteResolution remoteResolution = js.resolutionById(issuesByJql[0].getResolution());
        final List<Map<String, Object>> description = js.getIssuesByJql("project=LS and key=LS-62", 0, 100, "description,status", "operations,changelog");
        System.out.println(description);
    }

    @Test
    public void testRestGetVersions() throws IOException, RestException {
        RemoteVersion[] versions = js.getVersions("TST");
        Assert.assertTrue(versions.length > 0);
    }

    @Test
    public void testRestGetIssue() throws IOException, RestException {
        final RemoteIssueExt issue = js.getIssue("TST-1");
        final Map<String, Object> issue2 = js.getIssue("TST-1", "status,description", "operations,changelog");
    }

    @Test
    @Ignore
    public void testRestGetIssuesFromFilter() throws IOException, RestException {
        JiraService js = TestUtils.jiraService();
        final RemoteIssueExt[] issues = js.getIssuesFromFilter("Sibad | Resolved Bugs");
    }

    @Test
    public void testRest() throws IOException, RestException {
        final JiraRestService service = TestUtils.jiraRestService();
        for (RemoteIssueType type : js.getIssueTypes()) {
            final List<Map<String, Object>> ids = service.getAllIssuesByJql("type='" + type.getName() + "'", "id", null);
            System.out.println(type.getName() + ": " + ids.size());
        }
    }

    @Test
    @Ignore
    public void testExecuteImpl() throws Exception {
        JiraService service = TestUtils.jiraService();

        // final List allProjects = service.getAllProjects();

        RemotePriority[] priorities = service.getPriorities();
        RemoteIssue issue = service.getIssue("IPOM-23");
        final RemoteComment[] comments = service.getComments("IPOM-23");
        String s1 = service.fieldNameById("10040");
        String s = service.customFieldByName(issue, "Release Notes Comments");

        Object o = service.executeGet("search?jql=" + URLEncoder.encode("id in linkedissues(IPOM-53) and (type=\"Test Case\")", "utf-8"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List issues = (List) ((Map<String, Object>) o).get("issues");

        //      RemoteFieldValue[] desc = new RemoteFieldValue[]{new RemoteFieldValue("description", new String[]{issue.getDescription() + "äÄ"})};
//       RemoteIssue issue = service.updateIssue(issue.getKey(), desc);

        RemoteIssue issue2 = new RemoteIssue();
        issue2.setSummary("summary");
        issue2.setProject("IPOM");
        JiraTasks tasks = new JiraTasks(service);
        issue2.setType(tasks.issueTypeByName("Task").getId());
        issue2.setStatus("Closed");
        issue2.setDescription("Ää");
        issue2 = service.createIssue(issue2);

        RemoteAttachment attachment = service.getAttachmentsFromIssue("SIBAD-566")[0];
        InputStream in = service.loadAttachment(attachment);
        copy(in, new FileOutputStream(new File("target/" + attachment.getFilename())));

        List<IssueLinkType> issueLinkTypes = service.getIssueLinkTypes();
        service.linkIssues(new IssueLink("Blocking", "SIBAD-1103", "SIBAD-1104"));
        Object issueLinkType = service.executeGet("issueLinkType");
        Object user = service.executeGet("user?username=stni");
        System.out.println(issueLinkType);
    }

    private void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[10000];
        int read;
        while ((read = in.read(buf)) > 0) {
            out.write(buf, 0, read);
        }
        out.close();
    }
}
