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

import java.util.Locale

import com.atlassian.jira.rpc.soap.beans._
import guru.nidi.atlassian.remote.jira.RemoteIssueExt
import guru.nidi.text.transform.Name._
import guru.nidi.text.transform.Segment._
import guru.nidi.text.transform.format.SimpleFormatter
import guru.nidi.text.transform.parse.SimpleParser
import guru.nidi.text.transform.{Formatter, TransformContext}
import org.slf4j.LoggerFactory

/**
 *
 */
class JiraQuery(service: QueryService,
                maxResult: Int = 100,
                globalExpression: String = null,
                parser: QueryParser = new QueryParser(new SimpleParser(new TransformContext(0, Locale.ENGLISH, null))),
                formatter: Formatter = new SimpleFormatter) {

  private val log = LoggerFactory.getLogger(getClass)

  def projects(names: String*): QueryProjectList =
    QueryProjectList.ofRemoteProjects(this, service.getProjectsByKey(names: _*))

  def project(name: String): QueryProjectList = projects(name)(0)

  def allProjects: QueryProjectList = QueryProjectList.ofRemoteProjects(this, service.getProjectsByKey())

  def global: QueryProjectList = new QueryProjectList(this, List(new QueryProject(null)))

  def issue(issueKey: String): QueryIssueList = QueryIssueList.ofRemoteIssues(this, List(service.getIssue(issueKey)))

  private[query] def executeJql(jql: String): Seq[RemoteIssueExt] =
    service.getIssuesFromJqlSearch(JqlBuilder.and(globalExpression, jql), maxResult)

  def parseAndFormat(s: String, issue: QueryIssueList): String = {
    if (s == null) {
      ""
    } else {
      val root = parser.parse(s, issue)
      val formatted = formatter.format(root)
      log.trace("Formatting input\n{}\n****** parsed:\n{}\n****** formatted:\n{}\n******", Array(s, root, formatted))
      formatted
    }
  }

  def formatAsPlain(text: String): String = formatter.format(ROOT(plain(text)))

  def getBaseUrl: String = service.baseUrl

  def getCustomField(issue: RemoteIssue, name: String): String = service.customField(issue, name)

  def priorityById(id: String): RemotePriority = service.priorityById(id)

  def issueTypeById(id: String): RemoteIssueType = service.issueTypeById(id)
  
  def statusById(id: String): RemoteStatus = service.statusById(id)
  
  def resolutionById(id: String): RemoteResolution = service.resolutionById(id)
}

