package guru.nidi.atlassian.remote.query

import org.slf4j.LoggerFactory
import com.atlassian.jira.rpc.soap.beans.{RemoteIssueType, RemotePriority, RemoteIssue, RemoteProject}
import java.util.Collections
import collection.convert.Wrappers
import guru.nidi.atlassian.remote.jira.{RemoteIssueExt, JiraService}
import guru.nidi.atlassian.remote.AtlassianException

/**
 *
 */
class DefaultQueryService(service: JiraService) extends QueryService {
  private val log = LoggerFactory.getLogger(getClass)

  def getProjectsByKey(keys: String*): Seq[RemoteProject] = {
    Wrappers.JListWrapper(
      keys.length match {
        case 0 => wrap((dummy: String) => service.getAllProjects, null)
        case 1 => Collections.singletonList(wrap(service.getProjectByKey, keys(0)))
        case _ => wrap((keys: Seq[String]) => service.getProjectsByKey(keys: _*), keys)
      })
  }

  def getIssue(issueKey: String): RemoteIssueExt = wrap(service.getIssue, issueKey)

  def getIssuesFromJqlSearch(query: String, maxResults: Int): Seq[RemoteIssueExt] = {
    log.debug("Executing jql '{}'", query)
    wrap((q: String) => service.getIssuesByJql(q, 0, maxResults), query)
  }

  def baseUrl: String = service.getBaseUrl

  def customField(issue: RemoteIssue, name: String): String = wrap((i: RemoteIssue) => service.customFieldByName(i, name), issue)

  def priorityById(id: String): RemotePriority = wrap(service.priorityById, id)

  def issueTypeById(id: String): RemoteIssueType = wrap(service.issueTypeById, id)

  private def wrap[P, T](exec: P => T, param: P): T =
    try {
      exec(param)
    } catch {
      case e: AtlassianException => throw new QueryException(param.toString, e.getCause)
    }
}
