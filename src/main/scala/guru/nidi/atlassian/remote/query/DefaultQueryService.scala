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

import java.util.Collections

import com.atlassian.jira.rpc.soap.beans._
import guru.nidi.atlassian.remote.AtlassianException
import guru.nidi.atlassian.remote.jira.{JiraService, RemoteIssueExt}
import org.slf4j.LoggerFactory

import scala.collection.convert.Wrappers

/**
 *
 */
class DefaultQueryService(service: JiraService) extends QueryService {
  private val log = LoggerFactory.getLogger(getClass)

  def getProjectsByKey(keys: String*): Seq[RemoteProject] = {
    Wrappers.JListWrapper(
      keys.length match {
        case 0 => wrap((dummy: String) => service.getAllProjects, null)
        case 1 => Collections.singletonList(wrap(service.getProjectByKey, keys(0)))
        case _ => wrap((keys: Seq[String]) => service.getProjectsByKey(keys: _*), keys)
      })
  }

  def getIssue(issueKey: String): RemoteIssueExt = wrap(service.getIssue, issueKey)

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssueExt] = {
    log.debug("Executing jql '{}'", query)
    val res = wrap((q: String) => service.getIssuesByJql(q, 0, maxResults), query)
    if (res.size > 20) log.debug("Found {} results", res.size)
    res
  }

  def baseUrl: String = service.getBaseUrl

  def customField(issue: RemoteIssue, name: String): String = wrap((i: RemoteIssue) => service.customFieldByName(i, name), issue)

  def priorityById(id: String): RemotePriority = wrap(service.priorityById, id)

  def issueTypeById(id: String): RemoteIssueType = wrap(service.issueTypeById, id)
  
  def statusById(id: String): RemoteStatus = wrap(service.statusById, id)
  
  def resolutionById(id: String): RemoteResolution = wrap(service.resolutionById, id)

  private def wrap[P, T](exec: P => T, param: P): T =
    try {
      exec(param)
    } catch {
      case e: AtlassianException => throw new QueryException(param.toString, e.getCause)
    }
}
