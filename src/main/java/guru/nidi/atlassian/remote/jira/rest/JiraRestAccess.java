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
package guru.nidi.atlassian.remote.jira.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import guru.nidi.atlassian.remote.rest.RestAccess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class JiraRestAccess extends RestAccess{
    public static final TypeReference<HashMap<String, ArrayList<IssueLinkType>>> MAP_WITH_LINKTYPES_TYPE_REFERENCE = new TypeReference<HashMap<String, ArrayList<IssueLinkType>>>() {
    };

    public JiraRestAccess(String base, String username, String password) {
        super(base+"/rest/api/2/",username,password);
    }
}