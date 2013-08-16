package stni.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.{RemotePermissionScheme, RemoteScheme}

/**
 *
 */
abstract class QueryProjectWrapper(elems: Seq[QueryProject], t: QueryProject => QueryProjectList) extends AbstractQueryList[QueryProject, QueryProjectList](elems, t) {
  def project = elems(0).project

  def getDescription: String = project.getDescription

  def getIssueSecurityScheme: RemoteScheme = project.getIssueSecurityScheme

  def getKey: String = project.getKey

  def getLead: String = project.getLead

  def getNotificationScheme: RemoteScheme = project.getNotificationScheme

  def getPermissionScheme: RemotePermissionScheme = project.getPermissionScheme

  def getProjectUrl: String = project.getProjectUrl

  def getUrl: String = project.getUrl

  def getName: String = project.getName

  def getId: String = project.getId

  override def toString = "Project (key=" + getKey + ")"

}

