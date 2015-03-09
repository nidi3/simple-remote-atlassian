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

import guru.nidi.atlassian.remote.TestUtils;
import guru.nidi.atlassian.remote.meta.client.JiraExportClient;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class JiraExportClientTest {
    @Test
    @Ignore
    public void simple() throws IOException {
        final JiraExportClient client = new JiraExportClient("http://localhost:8080", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        final JiraGenerateRequest request = TestUtils.jiraGenerateRequest();
        request.setTemplate("rn");
        request.setStyle("mima");
        request.setVersions("Sprint 4");
        request.setProjectKey("IPOM");
        final InputStream export = client.getExport(request);
        System.out.println(export);
    }
}
