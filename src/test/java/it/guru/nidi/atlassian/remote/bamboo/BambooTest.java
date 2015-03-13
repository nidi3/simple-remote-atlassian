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
package it.guru.nidi.atlassian.remote.bamboo;

import guru.nidi.atlassian.remote.bamboo.BambooResultDetail;
import guru.nidi.atlassian.remote.bamboo.BambooService;
import guru.nidi.atlassian.remote.bamboo.rest.BambooPlan;
import guru.nidi.atlassian.remote.bamboo.rest.BambooResult;
import guru.nidi.atlassian.remote.bamboo.rest.BambooServerInfo;
import it.guru.nidi.atlassian.remote.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 *
 */
public class BambooTest {
    private static BambooService service;

    @BeforeClass
    public static void init() {
        service = TestUtils.bambooService();
        System.out.println("Start jira with 'mvn bamboo:run' or run as integration test.");
    }

    @Test
    public void serverInfo() {
        final BambooServerInfo info = service.getServerInfo();
        assertNotNull(info.getVersion());
    }

    @Test
    public void buildPlans() {
        final List<BambooPlan> buildPlans = service.getBuildPlans();
        assertFalse(buildPlans.isEmpty());
    }

    @Test
    public void results() {
        final List<BambooResult> results = service.getResults();
        assertFalse(results.isEmpty());
    }

    @Test
    public void resultDetail() {
        final BambooResultDetail detail = service.getResultDetail("AP-SUCCEED-2");
        assertNotNull(detail);
    }
}
