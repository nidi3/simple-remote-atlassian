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

import guru.nidi.atlassian.remote.AtlassianException;
import guru.nidi.atlassian.remote.bamboo.BambooResultDetail;
import guru.nidi.atlassian.remote.bamboo.BambooService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DefaultBambooService implements BambooService {
    private final BambooRestAccess access;

    public DefaultBambooService(String baseUrl, String username, String password) {
        access = new BambooRestAccess(baseUrl, username, password);
    }

    public BambooServerInfo getServerInfo() throws AtlassianException {
        try {
            return access.executeGet("info", null, BambooServerInfo.class);
        } catch (Exception e) {
            throw new AtlassianException("", e);
        }
    }

    @Override
    public List<BambooPlan> getBuildPlans() {
        try {
            final BambooPlansWrapper wrapper = access.executeGet("plan", params("max-result", 100), BambooPlansWrapper.class);
            return wrapper.getPlans().getPlan();
        } catch (Exception e) {
            throw new AtlassianException("", e);
        }
    }

    @Override
    public List<BambooResult> getResults() {
        try {
            final BambooResultsWrapper wrapper = access.executeGet("result", params("max-result", 100), BambooResultsWrapper.class);
            return wrapper.getResults().getResult();
        } catch (Exception e) {
            throw new AtlassianException("", e);
        }
    }

    @Override
    public BambooResultDetail getResultDetail(String key) {
        try {
            return access.executeGet("result/"+key,null, BambooResultDetail.class);
        } catch (Exception e) {
            throw new AtlassianException("", e);
        }
    }

    private Map<String, Object> params(Object... values) {
        Map<String, Object> res = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i += 2) {
            res.put((String) values[i], values[i + 1]);
        }
        return res;
    }
}