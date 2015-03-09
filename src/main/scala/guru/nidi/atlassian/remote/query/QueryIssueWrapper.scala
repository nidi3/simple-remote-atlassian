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

import java.util.Calendar

import com.atlassian.jira.rpc.soap.beans.{RemoteComponent, RemoteCustomFieldValue, RemoteVersion}

/**
 *
 */
abstract class QueryIssueWrapper(elems: Seq[QueryIssue], t: QueryIssue => QueryIssueList) extends AbstractQueryList[QueryIssue, QueryIssueList](elems, t) {
  def issue = elems(0).issue

  def getAffectsVersions: Array[RemoteVersion] = issue.getAffectsVersions

  def getCreated: Calendar = issue.getCreated

  def getStatus: String = issue.getStatus

  def getDuedate: Calendar = issue.getDuedate

  def getCustomFieldValues: Array[RemoteCustomFieldValue] = issue.getCustomFieldValues

  def getComponents: Array[RemoteComponent] = issue.getComponents

  def getEnvironment: String = issue.getEnvironment

  def getType: String = issue.getType

  def getAssignee: String = issue.getAssignee

  def getResolution: String = issue.getResolution

  def getPriority: String = issue.getPriority

  def getAttachmentNames: Array[String] = issue.getAttachmentNames

  def getFixVersions: Array[RemoteVersion] = issue.getFixVersions

  def getUpdated: Calendar = issue.getUpdated

  def getReporter: String = issue.getReporter

  def getId: String = issue.getId

  def getSummary: String = issue.getSummary

  def getVotes: Long = issue.getVotes

  def getProject: String = issue.getProject

  def getDescription: String = issue.getDescription

  def getKey: String = issue.getKey

  implicit def integer2OptionInt(i: Integer): Option[Int] = if (i == null) None else Some(i)

  def getTimeSpent: Option[Int] = issue.getTimeSpent

  def getTimeOriginalEstimate: Option[Int] = issue.getTimeOriginalEstimate

  def getAggregateTimeSpent: Option[Int] = issue.getAggregateTimeSpent

  def getAggregateTimeOriginalEstimate: Option[Int] = issue.getAggregateTimeOriginalEstimate

  override def toString = "Issue (key=" + getKey + ")"
}

