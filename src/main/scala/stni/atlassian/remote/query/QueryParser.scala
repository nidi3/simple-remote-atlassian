package stni.atlassian.remote.query

import stni.text.transform.{Attribute, Segment, Parser}

/**
 *
 */
class QueryParser(parser: Parser, preProcessor: PreProcessor = NoOpPreProcessor, exceptionHandler: ParseExceptionHandler = ThrowingParseExceptionHandler) {

  def parse(input: String, issue: QueryIssueList): Segment = {
    try {
      parser.parse(preProcessor.preProcess(input))(QueryParser.ISSUE -> issue)
    } catch {
      case e: Exception => exceptionHandler.handleException(input, issue, e)
    }
  }
}

object QueryParser {
  private val ISSUE = Attribute[QueryIssueList]("issue")

  def issueOf(segment: Segment): Option[QueryIssueList] = segment.inherited(ISSUE)
}

trait PreProcessor {
  def preProcess(input: String): String
}

object NoOpPreProcessor extends PreProcessor {
  def preProcess(input: String): String = input
}
