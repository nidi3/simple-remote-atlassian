package guru.nidi.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans._
import guru.nidi.atlassian.remote.jira.RemoteIssueExt

/**
 *
 */
trait QueryService {
  def getProjectsByKey(keys: String*): Seq[RemoteProject]

  def getIssue(issueKey: String): RemoteIssueExt

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssueExt]

  def baseUrl: String

  def customField(issue: RemoteIssue, name: String): String

  def priorityById(id: String): RemotePriority

  def issueTypeById(id: String): RemoteIssueType

  def statusById(id: String): RemoteStatus
  
  def resolutionById(id: String): RemoteResolution
}
