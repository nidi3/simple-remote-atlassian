package stni.atlassian.remote.query

import com.atlassian.jira.rpc.soap.beans.{RemoteComponent, RemoteCustomFieldValue, RemoteVersion}
import java.util.Calendar

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

  override def toString = "Issue (key=" + getKey + ")"
}

