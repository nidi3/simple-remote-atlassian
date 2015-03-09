/**
 * Copyright (C) 2013 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guru.nidi.atlassian.remote.query

import guru.nidi.text.transform.{Attribute, Parser, Segment}

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
