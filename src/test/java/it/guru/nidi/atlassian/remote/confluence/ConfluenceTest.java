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
package it.guru.nidi.atlassian.remote.confluence;

import com.atlassian.confluence.rpc.soap.beans.RemotePageSummary;
import com.atlassian.confluence.rpc.soap.beans.RemoteSpace;
import com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary;
import guru.nidi.atlassian.remote.confluence.ConfluenceService;
import it.guru.nidi.atlassian.remote.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 *
 */
public class ConfluenceTest {
    private static ConfluenceService service;

    @BeforeClass
    public static void init() {
        service = TestUtils.confluenceService();
        System.out.println("Start jira with 'mvn confluence:run' or run as integration test.");
    }

    @Test
    public void simple() {
        RemoteSpaceSummary[] spaces = service.getSpaces();
        assertEquals(1, spaces.length);
        assertEquals("Demonstration Space", spaces[0].getName());
        assertEquals("ds", spaces[0].getKey());

        RemoteSpace ds = service.getSpace("ds");
        assertNotNull(ds);
        final RemotePageSummary[] dses = service.getPages("ds");
        assertTrue(dses.length > 1);
    }
}
