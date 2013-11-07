package stni.atlassian.remote.query

import org.slf4j.LoggerFactory
import stni.text.transform.Segment

/**
 *
 */
trait ParseExceptionHandler {
  private val logger = LoggerFactory.getLogger(getClass)

  protected def log(issue: QueryIssueList, e: Exception) = logger.warn("Parsing problem with issue {}", issue, e)

  def handleException(input: String, issue: QueryIssueList, e: Exception): Segment
}

object ThrowingParseExceptionHandler extends ParseExceptionHandler {
  def handleException(input: String, issue: QueryIssueList, e: Exception) = throw new ParseException(issue, e)
}

object IgnoringParseExceptionHandler extends ParseExceptionHandler {
  def handleException(input: String, issue: QueryIssueList, e: Exception) = {
    log(issue, e)
    Segment.plain("")
  }
}

class RetryingParseExceptionHandler(parser: QueryParser) extends ParseExceptionHandler {
  def handleException(input: String, issue: QueryIssueList, e: Exception) = {
    log(issue, e)
    parser.parse(input, issue)
  }
}



