/**
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
package guru.nidi.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans._
import guru.nidi.atlassian.remote.jira.RemoteIssueExt

/**
 *
 */
class QueryServiceWrapper(val delegate: QueryService) extends QueryService {
  def getProjectsByKey(keys: String*): Seq[RemoteProject] = delegate.getProjectsByKey(keys: _*)

  def getIssue(issueKey: String): RemoteIssueExt = delegate.getIssue(issueKey)

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssueExt] = getIssuesFromJqlSearch(query, maxResults)

  def baseUrl: String = delegate.baseUrl

  def customField(issue: RemoteIssue, name: String): String = delegate.customField(issue, name)

  def priorityById(id: String): RemotePriority = delegate.priorityById(id)

  def issueTypeById(id: String): RemoteIssueType = delegate.issueTypeById(id)

  def statusById(id: String): RemoteStatus = delegate.statusById(id)
  
  def resolutionById(id: String): RemoteResolution = delegate.resolutionById(id)
}
