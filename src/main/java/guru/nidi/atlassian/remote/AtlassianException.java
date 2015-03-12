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
package guru.nidi.atlassian.remote;

import org.apache.axis.AxisFault;

/**
 *
 */
public class AtlassianException extends RuntimeException {
    public AtlassianException(String message, Throwable cause) {
        super(createMessage(message, cause), cause);
    }

    private static String createMessage(String message, Throwable cause) {
        if (cause instanceof AxisFault) {
            return message + " " + ((AxisFault) cause).getFaultString();
        }
        return message;
    }
}