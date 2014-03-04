package guru.nidi.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.{RemoteProject, RemotePriority, RemoteIssueType, RemoteIssue}

/**
 *
 */
class QueryServiceWrapper(val delegate: QueryService) extends QueryService {
  def getProjectsByKey(keys: String*): Seq[RemoteProject] = delegate.getProjectsByKey(keys: _*)

  def getIssue(issueKey: String): RemoteIssue = delegate.getIssue(issueKey)

  def getIssuesFromFilter(filter: String): Seq[RemoteIssue] = delegate.getIssuesFromFilter(filter)

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssue] = getIssuesFromJqlSearch(query, maxResults)

  def baseUrl: String = delegate.baseUrl

  def customField(issue: RemoteIssue, name: String): String = delegate.customField(issue, name)

  def priorityById(id: String): RemotePriority = delegate.priorityById(id)

  def issueTypeById(id: String): RemoteIssueType = delegate.issueTypeById(id)

  def timeToResolve(issue: RemoteIssue): Long = delegate.timeToResolve(issue)
}
