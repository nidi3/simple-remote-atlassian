package stni.atlassian.remote.query

import JqlBuilder._
import com.atlassian.jira.rpc.soap.beans.RemoteProject
import collection.convert.Wrappers

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
