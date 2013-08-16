package stni.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.{RemoteIssueType, RemotePriority, RemoteIssue}
import JqlBuilder._
import collection.convert.Wrappers

/**
 *
 */
class QueryIssueList(query: JiraQuery, elems: Seq[QueryIssue]) extends QueryIssueWrapper(elems, qi => new QueryIssueList(query, List(qi))) {

  import QueryIssueList._

  def children(expression: String, order: String): QueryIssueList =
    if (this.isEmpty) {
      ofQueryIssues(query)
    } else {
      ofJql(query, parentIn(this), expression, order)
    }

  def typedLinked(linkType: String, expression: String, order: String): QueryIssueList = {
    ofRemoteIssues(query, this.flatMap(elem => query.executeJql(jql(linkedWith(elem.getKey, linkType), expression, order))))
  }

  def asJList: java.util.List[QueryIssueList] = Wrappers.SeqWrapper(this)

  def formattedCustomField(name: String): String = query.parseAndFormat(customField(name), this)

  def customField(name: String): String = query.getCustomField(issue, name)

  def summary: String = query.formatAsPlain(super.getSummary)

  def description: String = query.parseAndFormat(super.getDescription, this)

  def url: String = url(getKey)

  def url(otherIssueKey: String): String = query.getBaseUrl + "/browse/" + otherIssueKey

  def priority: RemotePriority = query.priorityById(getPriority)

  def issueType: RemoteIssueType = query.issueTypeById(getType)

  def timeToResolve: Long = query.timeToResolve(issue)

}

object QueryIssueList {
  def ofQueryIssues(query: JiraQuery, elems: QueryIssue*): QueryIssueList = new QueryIssueList(query, elems)

  def ofJql(query: JiraQuery, context: String, expression: String, order: String): QueryIssueList =
    ofRemoteIssues(query, query.executeJql(jql(context, expression, order)))

  def ofRemoteIssues(query: JiraQuery, issues: Seq[RemoteIssue]): QueryIssueList =
    ofQueryIssues(query, issues.map(ri => new QueryIssue(ri)): _*)

}