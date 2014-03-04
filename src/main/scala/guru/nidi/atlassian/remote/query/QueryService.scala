package guru.nidi.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.{RemoteIssueType, RemotePriority, RemoteProject, RemoteIssue}

/**
 *
 */
trait QueryService {
  def getProjectsByKey(keys: String*): Seq[RemoteProject]

  def getIssue(issueKey: String): RemoteIssue

  def getIssuesFromFilter(filter: String): Seq[RemoteIssue]

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssue]

  def baseUrl: String

  def customField(issue: RemoteIssue, name: String): String

  def priorityById(id: String): RemotePriority

  def issueTypeById(id: String): RemoteIssueType

  def timeToResolve(issue: RemoteIssue): Long
}
