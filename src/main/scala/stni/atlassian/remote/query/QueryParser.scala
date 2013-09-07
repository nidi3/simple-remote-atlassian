package stni.atlassian.remote.query

import stni.text.transform.{Segment, Parser}

/**
 *
 */
class QueryParser(parser: Parser, preProcessor: PreProcessor = NoOpPreProcessor) {
  def parse(input: String): Segment = parser.parse(preProcessor.preProcess(input))
}

trait PreProcessor {
  def preProcess(input: String): String
}

object NoOpPreProcessor extends PreProcessor {
  def preProcess(input: String): String = input
}
