package guru.nidi.atlassian.remote.query

import org.scalatest.FlatSpec

/**
 *
 */
class JqlBuilderTest extends FlatSpec {

  behavior of "JqlBuilder"

  it should "concat query correctly with and" in {
    assert(JqlBuilder.and("a", "b") === "(a) and (b)")
    assert(JqlBuilder.and("", "b") === "b")
    assert(JqlBuilder.and(null, "b") === "b")
    assert(JqlBuilder.and("a", "") === "a")
    assert(JqlBuilder.and("a", null) === "a")
  }

  it should "concat order correctly with and" in {
    assert(JqlBuilder.and("a order by c", "b") === "(a) and (b) order by c")
    assert(JqlBuilder.and("a", "b order by c") === "(a) and (b) order by c")
    assert(JqlBuilder.and("a order by c", "b order by d") === "(a) and (b) order by c,d")
  }

  it should "construct correct jql strings" in {
    assert(JqlBuilder.jql("a", "b", null) === "(a) and (b)")
    assert(JqlBuilder.jql("a", "b", "c") === "(a) and (b) order by c")
  }
}
