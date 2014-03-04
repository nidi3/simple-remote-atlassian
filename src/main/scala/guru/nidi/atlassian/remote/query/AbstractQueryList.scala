package guru.nidi.atlassian.remote.query

import collection.SeqProxy

/**
 *
 */
abstract class AbstractQueryList[S, T](val elems: Seq[S], t: S => T) extends QueryList[S, T] with SeqProxy[T] {
  lazy val self: Seq[T] = elems.map(t)

  override def equals(a: Any) = a.getClass == getClass && a.asInstanceOf[AbstractQueryList[S, T]].elems == elems
}
