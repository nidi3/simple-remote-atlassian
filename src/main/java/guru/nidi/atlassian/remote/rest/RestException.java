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
package guru.nidi.atlassian.remote.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class RestException extends Exception {
    private final Map<String, Object> values;
    private List<String> errorMessages = Collections.emptyList();

    public RestException(String message) {
        this(message, null);
    }

    public RestException(String message, Throwable cause) {
        super(cause);
        values = new HashMap<String, Object>();
        errorMessages = Collections.singletonList(message);
    }

    public RestException(Map<String, Object> values) {
        this.values = values;
        Object errorMessageObj = values.get("errorMessages");
        if (errorMessageObj instanceof List) {
            errorMessages = (List<String>) errorMessageObj;
        }
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public String getMessage() {
        return errorMessages.toString();
    }
}
