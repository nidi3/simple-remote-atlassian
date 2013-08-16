package stni.atlassian.remote.query

/**
 *
 */
object JqlBuilder {
  def and(expression: String): String =
    if (!empty(expression)) {
      " and (" + expression + ")"
    } else {
      ""
    }

  private def orderBy(expression: String): String =
    if (!empty(expression)) {
      " order by " + expression
    } else {
      ""
    }

  def parentIn(issues: QueryIssueList): String = "parent in (" + issues.map(i => i.getKey).mkString(",") + ")"

  def projectIn(projects: QueryProjectList): String = "project in (" + projects.map(p => p.getKey).mkString(",") + ")"

  def linkedWith(issueKey: String, linkType: String): String = {
    "id in linkedissues(" + issueKey + (
      if (linkType != null) {
        ",'" + linkType + "')"
      } else {
        ")"
      })
  }

  def jql(context: String, query: String, order: String): String = {
    if (context == null) {
      query + orderBy(order)
    } else {
      context + and(query) + orderBy(order)
    }
  }

  private def empty(s: String): Boolean = s == null || s.length == 0

}
