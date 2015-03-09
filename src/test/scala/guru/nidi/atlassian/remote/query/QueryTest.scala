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
import guru.nidi.atlassian.remote.query.QueryIssueList._
import guru.nidi.atlassian.remote.query.QueryProjectList._
import org.scalatest.FlatSpec

/**
 *
 */
class QueryTest extends FlatSpec {
  def issue(key: String) = {
    val issue = new RemoteIssueExt()
    issue.setKey(key)
    issue
  }

  def project(key: String) = {
    val project = new RemoteProject()
    project.setKey(key)
    project
  }

  val qs = new QueryService {
    var lastQuery: String = null

    def getIssue(issueKey: String): RemoteIssueExt = issue(issueKey)

    def customField(issue: RemoteIssue, name: String): String = issue.getKey + name

    def getProjectsByKey(keys: String*): Seq[RemoteProject] = if (keys.length == 0) List(project("a"), project("b")) else keys.map(key => project(key))

    def baseUrl: String = "base"

    def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssueExt] = {
      lastQuery = query
      Nil
    }

    def priorityById(id: String): RemotePriority = new RemotePriority(id, id, "", "", "")

    def issueTypeById(id: String): RemoteIssueType = new RemoteIssueType(id, id, "", "", false)
    
    def statusById(id: String): RemoteStatus = new RemoteStatus(id, id, "", null)
    
    def resolutionById(id: String): RemoteResolution = new RemoteResolution(id, id, "", null)
  }

  val jq = new JiraQuery(qs)

  behavior of "Jira remote"

  it should "support its entry functions" in {
    assert(jq.projects() === ofRemoteProjects(jq, List(project("a"), project("b"))))
    assert(jq.allProjects === ofRemoteProjects(jq, List(project("a"), project("b"))))
    assert(jq.projects("c") === ofRemoteProjects(jq, List(project("c"))))
    assert(jq.project("c") === ofRemoteProjects(jq, List(project("c"))))
    assert(jq.issue("key") == ofRemoteIssues(jq, List(issue("key"))))
    assert(jq.getCustomField(issue("my"), "field") === "myfield")
  }

  behavior of "QueryProject"

  it should "delegate to a RemoteProject" in {
    assert(jq.project("x").getKey === "x")
  }

  it should "be a list with one entry" in {
    assert(jq.project("x").length === 1)
    assert(jq.project("x")(0).getKey === "x")
  }

  it should "implement QueryList" in {
    jq.project("x").children("a=b")
    assert(qs.lastQuery === "(project in (x)) and (a=b)")

    assert(jq.project("x").linked("a=b").isEmpty)
  }

  behavior of "QueryProjectList"

  it should "be a list" in {
    assert(jq.projects().length === 2)
    assert(jq.projects()(1).getKey === "b")
  }

  it should "implement QueryList" in {
    jq.projects().children("a=b")
    assert(qs.lastQuery === "(project in (a,b)) and (a=b)")

    assert(jq.projects().linked("a=b").isEmpty)
  }

  behavior of "QueryIssue"

  it should "delegate to a RemoteIssue" in {
    assert(jq.issue("x").getKey === "x")
  }

  it should "be a list with one entry" in {
    assert(jq.project("x").length === 1)
    assert(jq.project("x")(0).getKey === "x")
  }

  it should "implement QueryList" in {
    jq.project("x").children("a=b")
    assert(qs.lastQuery === "(project in (x)) and (a=b)")

    assert(jq.project("x").linked("a=b").isEmpty)
  }
}
