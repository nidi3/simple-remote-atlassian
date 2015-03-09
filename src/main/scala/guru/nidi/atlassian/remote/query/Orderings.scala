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

import java.util.{Calendar, Date}

import com.atlassian.jira.rpc.soap.beans.RemoteVersion

/**
 *
 */
object Orderings {
  def negativeWrapping(value: String): Double = handleNegatives(numericValue(value))

  val versionOldFirst = new Ordering[RemoteVersion] {
    def compare(v1: RemoteVersion, v2: RemoteVersion): Int =
      timeOf(v1) compareTo timeOf(v2)

    private def timeOf(version: RemoteVersion): Long =
      timeOfCalendar(if (version == null) null else version.getReleaseDate)

    private def timeOfCalendar(cal: Calendar): Long =
      if (cal == null) new Date(200, 1, 1).getTime
      else cal.getTimeInMillis
  }

  val versionOldLast = versionOldFirst.reverse

  private def numericValue(value: String) = {
    if (value == null) 1000D
    else {
      try {
        value.toDouble
      } catch {
        case e: NumberFormatException => 1000
      }
    }
  }

  private def handleNegatives(value: Double) = if (value < 0) 2000 + value else value
}
