package stni.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.RemoteIssue

/**
 *
 */
private[query] class QueryIssue(val issue: RemoteIssue) {
  override def equals(obj: Any): Boolean = obj.getClass == getClass && obj.asInstanceOf[QueryIssue].issue == issue

  override def toString: String = issue.getKey
}