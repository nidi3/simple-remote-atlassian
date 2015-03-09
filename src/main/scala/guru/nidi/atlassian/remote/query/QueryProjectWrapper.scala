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

