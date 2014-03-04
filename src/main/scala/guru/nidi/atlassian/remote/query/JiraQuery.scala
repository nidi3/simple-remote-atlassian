package guru.nidi.atlassian.remote.query

import org.slf4j.LoggerFactory
import guru.nidi.text.transform.{TransformContext, Formatter}
import guru.nidi.text.transform.Name._
import guru.nidi.text.transform.Segment._
import guru.nidi.text.transform.parse.SimpleParser
import guru.nidi.text.transform.format.SimpleFormatter
import com.atlassian.jira.rpc.soap.beans.{RemoteIssueType, RemotePriority, RemoteIssue}
import java.util.Locale

/**
 *
 */
class JiraQuery(service: QueryService,
                maxResult: Int = 100,
                globalExpression: String = null,
                parser: QueryParser = new QueryParser(new SimpleParser(new TransformContext(0, Locale.ENGLISH, null))),
                formatter: Formatter = new SimpleFormatter) {

  private val log = LoggerFactory.getLogger(getClass)

  def issuesOfFilter(name: String): QueryIssueList =
    QueryIssueList.ofRemoteIssues(this, service.getIssuesFromFilter(name))

  def projects(names: String*): QueryProjectList =
    QueryProjectList.ofRemoteProjects(this, service.getProjectsByKey(names: _*))

  def project(name: String): QueryProjectList = projects(name)(0)

  def allProjects: QueryProjectList = QueryProjectList.ofRemoteProjects(this, service.getProjectsByKey())

  def global: QueryProjectList = new QueryProjectList(this, List(new QueryProject(null)))

  def issue(issueKey: String): QueryIssueList = QueryIssueList.ofRemoteIssues(this, List(service.getIssue(issueKey)))

  private[query] def executeJql(jql: String): Seq[RemoteIssue] =
    service.getIssuesFromJqlSearch(JqlBuilder.and(globalExpression, jql), maxResult)

  def parseAndFormat(s: String, issue: QueryIssueList): String = {
    if (s == null) {
      ""
    } else {
      val root = parser.parse(s, issue)
      val formatted = formatter.format(root)
      log.trace("Formatting input\n{}\n****** parsed:\n{}\n****** formatted:\n{}\n******", Array(s, root, formatted))
      formatted
    }
  }

  def formatAsPlain(text: String): String = formatter.format(ROOT(plain(text)))

  def getBaseUrl: String = service.baseUrl

  def getCustomField(issue: RemoteIssue, name: String): String = service.customField(issue, name)

  def priorityById(id: String): RemotePriority = service.priorityById(id)

  def issueTypeById(id: String): RemoteIssueType = service.issueTypeById(id)

  def timeToResolve(issue: RemoteIssue): Long = service.timeToResolve(issue)
}

