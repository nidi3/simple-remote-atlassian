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

/**
 * TODO use Jql everywhere instead of JqlBuilder, except in public methods
 */
object JqlBuilder {
  def and(a: String, b: String): String = Jql(a).and(Jql(b)).string

  private def orderBy(a: String, order: String): String = Jql(a).and(Jql(null, order)).string

  def parentIn(issues: QueryIssueList): String = "parent in (" + issues.map(i => i.getKey).mkString(",") + ")"

  def epicLinkIn(issues: QueryIssueList): String = "\"Epic Link\" in (" + issues.map(i => i.getKey).mkString(",") + ")"

  def projectIn(projects: QueryProjectList): String = "project in (" + projects.map(p => p.getKey).mkString(",") + ")"

  def linkedWith(issueKey: String, linkType: String): String = {
    val typ = if (linkType != null) ",'" + linkType + "'" else ""
    "id in linkedissues(" + issueKey + typ + ")"
  }

  def jql(context: String, query: String, order: String): String = {
    if (context == null) {
      orderBy(query, order)
    } else {
      orderBy(and(context, query), order)
    }
  }
}

class Jql(val query: String, val order: List[String]) {
  def op(jql: Jql, op: String) = {
    val newQuery =
      if (hasQuery && jql.hasQuery) s"($query) $op (${jql.query})"
      else if (hasQuery) query else jql.query
    new Jql(newQuery, order ++ jql.order)
  }

  def and(jql: Jql) = op(jql, "and")

  def or(jql: Jql) = op(jql, "or")

  def string = query + (if (!order.isEmpty) Jql.ORDER_BY + order.mkString(",") else "")

  def hasQuery = query.length > 0
}

object Jql {
  val ORDER_BY = " order by "

  def apply(query: String, order: String) = new Jql(nullSafe(query), if (nullSafe(order).length == 0) Nil else nullSafe(order).split(',').toList)

  def apply(q: String): Jql = {
    val nq = nullSafe(q)
    val p = nq.indexOf(ORDER_BY)
    if (p < 0) new Jql(nq, Nil)
    else Jql(nq.substring(0, p), nq.substring(p + ORDER_BY.length))
  }

  def nullSafe(s: String) = if (s == null) "" else s
}