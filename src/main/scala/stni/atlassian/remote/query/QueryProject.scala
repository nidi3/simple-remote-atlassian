package stni.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.RemoteProject

/**
 *
 */
private[query] class QueryProject(val project: RemoteProject) {
  override def equals(obj: Any): Boolean = obj.getClass == getClass && obj.asInstanceOf[QueryProject].project == project

}
