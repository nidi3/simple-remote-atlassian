package stni.atlassian.remote.query

import stni.text.transform.{Attribute, Segment, Parser}

/**
 *
 */
class QueryParser(parser: Parser, preProcessor: PreProcessor = NoOpPreProcessor, exceptionHandler: ParseExceptionHandler = ThrowingParseExceptionHandler) {
  private val ISSUE = Attribute("issue")

  def parse(input: String, issue: QueryIssueList): Segment = {
    try {
      parser.parse(preProcessor.preProcess(input))(ISSUE -> issue)
    } catch {
      case e: Exception => exceptionHandler.handleException(input, issue, e)
    }
  }
}

trait PreProcessor {
  def preProcess(input: String): String
}

object NoOpPreProcessor extends PreProcessor {
  def preProcess(input: String): String = input
}
