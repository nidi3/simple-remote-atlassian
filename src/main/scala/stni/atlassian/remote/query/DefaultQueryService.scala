package stni.atlassian.remote.query

import org.slf4j.LoggerFactory
import com.atlassian.jira.rpc.soap.beans.{RemoteIssueType, RemotePriority, RemoteIssue, RemoteProject}
import java.util.Collections
import collection.convert.Wrappers
import stni.atlassian.remote.jira.JiraService

/**
 *
 */
class DefaultQueryService(service: JiraService) extends QueryService {
  private val log = LoggerFactory.getLogger(getClass)

  def getProjectsByKey(keys: String*): Seq[RemoteProject] = {
    Wrappers.JListWrapper(
      keys.length match {
        case 0 => service.getAllProjects
        case 1 => Collections.singletonList(service.getProjectByKey(keys(0)))
        case _ => service.getProjectsByKey(keys: _*)
      })
  }

  def getIssue(issueKey: String): RemoteIssue = service.getIssue(issueKey)

  def getIssuesFromFilter(filter: String): Seq[RemoteIssue] = service.getIssuesFromFilter(filter)

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssue] = {
    log.debug("Executing jql '{}'", query)
    service.getIssuesFromJqlSearch(query, maxResults)
  }

  def baseUrl: String = service.getBaseUrl

  def customField(issue: RemoteIssue, name: String): String = service.customFieldByName(issue, name)

  def priorityById(id: String): RemotePriority = service.priorityById(id)

  def issueTypeById(id: String): RemoteIssueType = service.issueTypeById(id)

  def timeToResolve(issue: RemoteIssue): Long = service.timeToResolve(issue)
}
