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
package guru.nidi.atlassian.remote.bamboo.rest;

import guru.nidi.atlassian.remote.TestUtils;
import guru.nidi.atlassian.remote.bamboo.BambooResultDetail;
import guru.nidi.atlassian.remote.bamboo.BambooService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 *
 */
public class BambooTest {
    private BambooService service = TestUtils.bambooService();

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
    @Ignore
    public void resultDetail() {
        final BambooResultDetail detail = service.getResultDetail("IPOM-NIGHTLY-221");
        assertNotNull(detail);
    }
}
