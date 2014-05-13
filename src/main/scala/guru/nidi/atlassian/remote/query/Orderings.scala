package guru.nidi.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.RemoteVersion
import java.util.{Date, Calendar}

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
