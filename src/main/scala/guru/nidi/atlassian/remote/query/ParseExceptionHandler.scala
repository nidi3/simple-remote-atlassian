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

import guru.nidi.text.transform.Segment
import org.slf4j.LoggerFactory

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



