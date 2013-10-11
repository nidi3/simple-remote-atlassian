package stni.atlassian.remote.query

import org.slf4j.LoggerFactory
import stni.text.transform.{TransformContext, Formatter, Attribute}
import stni.text.transform.Name._
import stni.text.transform.Segment._
import stni.text.transform.parse.SimpleParser
import stni.text.transform.format.SimpleFormatter
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

  import JiraQuery._

  private val log = LoggerFactory.getLogger(getClass)

  def issuesOfFilter(name: String): QueryIssueList =
    QueryIssueList.ofRemoteIssues(this, service.getIssuesFromFilter(name))

  def projects(names: String*): QueryProjectList =
    QueryProjectList.ofRemoteProjects(this, service.getProjectsByKey(names: _*))

  def project(name: String): QueryProjectList = projects(name)(0)

  def allProjects: QueryProjectList = QueryProjectList.ofRemoteProjects(this, service.getProjectsByKey())

  def global: QueryProjectList = new QueryProjectList(this, List(new QueryProject(null)))

  def issue(issueKey: String): QueryIssueList = QueryIssueList.ofRemoteIssues(this, List(service.getIssue(issueKey)))

  private[query] def executeJql(s: String): Seq[RemoteIssue] =
    service.getIssuesFromJqlSearch(s + JqlBuilder.and(globalExpression), maxResult)

  def parseAndFormat(s: String, issue: QueryIssueList): String = {
    def parse = {
      try {
        parser.parse(s)(ISSUE -> issue)
      } catch {
        case e: Exception => throw new ParseException(issue, e)
      }
    }

    if (s == null) {
      ""
    } else {
      val root = parse
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

object JiraQuery {
  val ISSUE = Attribute("issue")
}
