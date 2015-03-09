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
