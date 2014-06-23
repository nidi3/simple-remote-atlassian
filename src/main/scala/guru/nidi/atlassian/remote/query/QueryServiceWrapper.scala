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

  def resolutionById(id: String): RemoteResolution = delegate.resolutionById(id)

}
