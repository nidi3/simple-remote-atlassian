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
package guru.nidi.atlassian.remote.confluence;

import com.atlassian.confluence.rpc.soap.beans.RemotePage;
import com.atlassian.confluence.rpc.soap.beans.RemoteSpace;
import com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary;
import guru.nidi.atlassian.remote.TestUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class ConfluenceTest {
    @Test
    @Ignore
    public void simple() {
        ConfluenceService service = TestUtils.confluenceService();
        RemoteSpaceSummary[] spaces = service.getSpaces();
        RemoteSpace moa = service.getSpace("LIVINGSERVICES");
        RemotePage page = service.getPage("IPOM", "Release notes");
        System.out.println(page.getContent());

        page = service.getPage("LIVINGSERVICES", "Einführung und Ziele");
        System.out.println(page.getContent());

        page = service.getPage("LIVINGSERVICES", "Projektaspekte");
        System.out.println(page.getContent());

        page = service.getPage("LIVINGSERVICES", "Randbedingungen");
        System.out.println(page.getContent());
    }
}
