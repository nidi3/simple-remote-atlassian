package stni.atlassian.remote.query

/**
 *
 */
object Orderings {
  def negativeWrapping(value: String): Double = handleNegatives(numericValue(value))

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
