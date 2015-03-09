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
package guru.nidi.atlassian.remote.bamboo;

import guru.nidi.atlassian.remote.bamboo.rest.BambooPlan;
import guru.nidi.atlassian.remote.bamboo.rest.BambooResult;
import guru.nidi.atlassian.remote.bamboo.rest.BambooServerInfo;

import java.util.List;

/**
 *
 */
public interface BambooService {
    BambooServerInfo getServerInfo();

    List<BambooPlan> getBuildPlans();

    List<BambooResult> getResults();

    BambooResultDetail getResultDetail(String key);
}
