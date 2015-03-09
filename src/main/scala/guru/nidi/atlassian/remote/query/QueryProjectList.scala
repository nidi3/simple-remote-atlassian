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

import com.atlassian.jira.rpc.soap.beans.RemoteProject
import guru.nidi.atlassian.remote.query.JqlBuilder._

import scala.collection.convert.Wrappers

/**
 *
 */
class QueryProjectList(query: JiraQuery, elems: Seq[QueryProject]) extends QueryProjectWrapper(elems, qp => new QueryProjectList(query, List(qp))) {
  def children(expression: String, order: String): QueryIssueList = {
    if (this.isEmpty) {
      QueryIssueList.ofQueryIssues(query)
    } else {
      QueryIssueList.ofJql(query, projectIn(this), expression, order)
    }
  }

  def typedLinked(linkType: String, expression: String, order: String): QueryProjectList =
    new QueryProjectList(query, Nil)

  def asJList = Wrappers.SeqWrapper(this)

}

object QueryProjectList {
  def ofRemoteProjects(query: JiraQuery, projects: Seq[RemoteProject]): QueryProjectList =
    new QueryProjectList(query, projects.map(rp => new QueryProject(rp)))

}
