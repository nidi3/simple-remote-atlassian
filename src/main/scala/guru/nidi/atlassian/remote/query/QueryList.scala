package guru.nidi.atlassian.remote.query

/**
 *
 */
trait QueryList[S, T] extends Seq[T] {
  def children(expression: String, order: String = null): QueryIssueList

  def linked(expression: String, order: String = null) = typedLinked(null, expression, order)

  def typedLinked(linkType: String, expression: String, order: String = null): QueryList[S, T]
}
