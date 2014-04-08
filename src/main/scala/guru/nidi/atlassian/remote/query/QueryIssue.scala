package guru.nidi.atlassian.remote.query

import guru.nidi.atlassian.remote.jira.RemoteIssueExt

/**
 *
 */
private[query] class QueryIssue(val issue: RemoteIssueExt) {
  override def equals(obj: Any): Boolean = obj.getClass == getClass && obj.asInstanceOf[QueryIssue].issue == issue

  override def toString: String = issue.getKey
}