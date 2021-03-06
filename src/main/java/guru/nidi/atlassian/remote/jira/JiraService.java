/*
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
package guru.nidi.atlassian.remote.jira;
public interface JiraService{
  String getBaseUrl();
  public java.lang.Object executeGet(java.lang.String string0);
  public java.lang.Object executePost(java.lang.String string0,java.lang.Object object1);
  public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAllIssuesByJql(java.lang.String string0,java.lang.String string1,java.lang.String string2);
  public java.util.List<com.atlassian.jira.rpc.soap.beans.RemoteProject> getAllProjects();
  public guru.nidi.atlassian.remote.jira.RemoteIssueExt getIssue(java.lang.String string0);
  public java.util.Map<java.lang.String, java.lang.Object> getIssue(java.lang.String string0,java.lang.String string1,java.lang.String string2);
  public java.util.List<guru.nidi.atlassian.remote.jira.rest.IssueLinkType> getIssueLinkTypes();
  public guru.nidi.atlassian.remote.jira.RemoteIssueExt[] getIssuesByJql(java.lang.String string0,int int1,int int2);
  public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getIssuesByJql(java.lang.String string0,int int1,int int2,java.lang.String string3,java.lang.String string4);
  public guru.nidi.atlassian.remote.jira.RemoteIssueExt[] getIssuesFromFilter(java.lang.String string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteProject getProjectByKey(java.lang.String string0);
  public java.util.List<com.atlassian.jira.rpc.soap.beans.RemoteProject> getProjectsByKey(java.lang.String... string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteVersion[] getVersions(java.lang.String string0);
  public void linkIssues(guru.nidi.atlassian.remote.jira.rest.IssueLink issueLink0);
  public java.io.InputStream loadAttachment(com.atlassian.jira.rpc.soap.beans.RemoteAttachment remoteAttachment0);
  public com.atlassian.jira.rpc.soap.beans.RemoteNamedObject actionByName(java.lang.String string0,java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue createRequirementAndFeature(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,java.lang.String string1,java.lang.String string2,java.lang.String string3);
  public java.lang.String customFieldByName(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue0,java.lang.String string1);
  public java.lang.String fieldNameById(java.lang.String string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssues(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion1,boolean boolean2);
  public guru.nidi.atlassian.remote.jira.JiraService getService();
  public com.atlassian.jira.rpc.soap.beans.RemoteIssueType issueTypeById(java.lang.String string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssueType issueTypeByName(java.lang.String string0);
  public java.lang.String makeReleaseNotes(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion1,boolean boolean2);
  public com.atlassian.jira.rpc.soap.beans.RemotePriority priorityById(java.lang.String string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue progressStatusAction(java.lang.String string0,java.lang.String string1,java.lang.String string2);
  public com.atlassian.jira.rpc.soap.beans.RemoteResolution resolutionById(java.lang.String string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteSecurityLevel securityLevelByName(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteStatus statusById(java.lang.String string0);
  public com.atlassian.jira.rpc.soap.beans.RemoteStatus statusByName(java.lang.String string0);
  public long timeToResolve(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue0);
  public com.atlassian.jira.rpc.soap.beans.RemoteVersion versionByName(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,java.lang.String string1);
  public void addActorsToProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject3,java.lang.String string4);
  public boolean addAttachmentsToIssue(java.lang.String string1,java.lang.String[] string2,byte[][] byte3);
  public boolean addBase64EncodedAttachmentsToIssue(java.lang.String string1,java.lang.String[] string2,java.lang.String[] string3);
  public void addComment(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteComment remoteComment2);
  public void addDefaultActorsToProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,java.lang.String string3);
  public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme addPermissionTo(com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme remotePermissionScheme1,com.atlassian.jira.rpc.soap.beans.RemotePermission remotePermission2,com.atlassian.jira.rpc.soap.beans.RemoteEntity remoteEntity3);
  public void addUserToGroup(com.atlassian.jira.rpc.soap.beans.RemoteGroup remoteGroup1,com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser2);
  public com.atlassian.jira.rpc.soap.beans.RemoteVersion addVersion(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion2);
  public com.atlassian.jira.rpc.soap.beans.RemoteWorklog addWorklogAndAutoAdjustRemainingEstimate(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog2);
  public com.atlassian.jira.rpc.soap.beans.RemoteWorklog addWorklogAndRetainRemainingEstimate(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog2);
  public com.atlassian.jira.rpc.soap.beans.RemoteWorklog addWorklogWithNewRemainingEstimate(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog2,java.lang.String string3);
  public void archiveVersion(java.lang.String string1,java.lang.String string2,boolean boolean3);
  public com.atlassian.jira.rpc.soap.beans.RemoteGroup createGroup(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser2);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssue(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssueWithParent(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1,java.lang.String string2);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssueWithParentWithSecurityLevel(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1,java.lang.String string2,long long3);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssueWithSecurityLevel(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1,long long2);
  public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme createPermissionScheme(java.lang.String string1,java.lang.String string2);
  public com.atlassian.jira.rpc.soap.beans.RemoteProject createProject(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4,java.lang.String string5,com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme remotePermissionScheme6,com.atlassian.jira.rpc.soap.beans.RemoteScheme remoteScheme7,com.atlassian.jira.rpc.soap.beans.RemoteScheme remoteScheme8);
  public com.atlassian.jira.rpc.soap.beans.RemoteProject createProjectFromObject(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject1);
  public com.atlassian.jira.rpc.soap.beans.RemoteProjectRole createProjectRole(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1);
  public com.atlassian.jira.rpc.soap.beans.RemoteUser createUser(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4);
  public void deleteGroup(java.lang.String string1,java.lang.String string2);
  public void deleteIssue(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme deletePermissionFrom(com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme remotePermissionScheme1,com.atlassian.jira.rpc.soap.beans.RemotePermission remotePermission2,com.atlassian.jira.rpc.soap.beans.RemoteEntity remoteEntity3);
  public void deletePermissionScheme(java.lang.String string1);
  public void deleteProject(java.lang.String string1);
  public void deleteProjectAvatar(long long1);
  public void deleteProjectRole(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1,boolean boolean2);
  public void deleteUser(java.lang.String string1);
  public void deleteWorklogAndAutoAdjustRemainingEstimate(java.lang.String string1);
  public void deleteWorklogAndRetainRemainingEstimate(java.lang.String string1);
  public void deleteWorklogWithNewRemainingEstimate(java.lang.String string1,java.lang.String string2);
  public com.atlassian.jira.rpc.soap.beans.RemoteComment editComment(com.atlassian.jira.rpc.soap.beans.RemoteComment remoteComment1);
  public com.atlassian.jira.rpc.soap.beans.RemotePermission[] getAllPermissions();
  public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getAssociatedNotificationSchemes(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1);
  public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getAssociatedPermissionSchemes(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1);
  public com.atlassian.jira.rpc.soap.beans.RemoteAttachment[] getAttachmentsFromIssue(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteNamedObject[] getAvailableActions(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteComment getComment(long long1);
  public com.atlassian.jira.rpc.soap.beans.RemoteComment[] getComments(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteComponent[] getComponents(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteConfiguration getConfiguration();
  public com.atlassian.jira.rpc.soap.beans.RemoteField[] getCustomFields();
  public com.atlassian.jira.rpc.soap.beans.RemoteRoleActors getDefaultRoleActors(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1);
  public com.atlassian.jira.rpc.soap.beans.RemoteFilter[] getFavouriteFilters();
  public com.atlassian.jira.rpc.soap.beans.RemoteField[] getFieldsForAction(java.lang.String string1,java.lang.String string2);
  public com.atlassian.jira.rpc.soap.beans.RemoteField[] getFieldsForCreate(java.lang.String string1,long long2);
  public com.atlassian.jira.rpc.soap.beans.RemoteField[] getFieldsForEdit(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteGroup getGroup(java.lang.String string1);
    public com.atlassian.jira.rpc.soap.beans.RemoteIssue getIssueById(java.lang.String string1);
  public long getIssueCountForFilter(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getIssueTypes();
  public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getIssueTypesForProject(java.lang.String string1);
    public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromFilterWithLimit(java.lang.String string1,int int2,int int3);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromJqlSearch(java.lang.String string1,int int2);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromTextSearch(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromTextSearchWithLimit(java.lang.String string1,int int2,int int3);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromTextSearchWithProject(java.lang.String[] string1,java.lang.String string2,int int3);
  public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getNotificationSchemes();
  public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme[] getPermissionSchemes();
  public com.atlassian.jira.rpc.soap.beans.RemotePriority[] getPriorities();
  public com.atlassian.jira.rpc.soap.beans.RemoteAvatar getProjectAvatar(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteAvatar[] getProjectAvatars(java.lang.String string1,boolean boolean2);
  public com.atlassian.jira.rpc.soap.beans.RemoteProject getProjectById(long long1);
    public com.atlassian.jira.rpc.soap.beans.RemoteProjectRole getProjectRole(long long1);
  public com.atlassian.jira.rpc.soap.beans.RemoteProjectRoleActors getProjectRoleActors(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1,com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject2);
  public com.atlassian.jira.rpc.soap.beans.RemoteProjectRole[] getProjectRoles();
  public com.atlassian.jira.rpc.soap.beans.RemoteProject getProjectWithSchemesById(long long1);
  public com.atlassian.jira.rpc.soap.beans.RemoteProject[] getProjectsNoSchemes();
  public java.util.Calendar getResolutionDateById(long long1);
  public java.util.Calendar getResolutionDateByKey(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteResolution[] getResolutions();
  public com.atlassian.jira.rpc.soap.beans.RemoteFilter[] getSavedFilters();
  public com.atlassian.jira.rpc.soap.beans.RemoteSecurityLevel getSecurityLevel(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteSecurityLevel[] getSecurityLevels(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getSecuritySchemes();
  public com.atlassian.jira.rpc.soap.beans.RemoteServerInfo getServerInfo();
  public com.atlassian.jira.rpc.soap.beans.RemoteStatus[] getStatuses();
  public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getSubTaskIssueTypes();
  public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getSubTaskIssueTypesForProject(java.lang.String string1);
  public com.atlassian.jira.rpc.soap.beans.RemoteUser getUser(java.lang.String string1);
    public com.atlassian.jira.rpc.soap.beans.RemoteWorklog[] getWorklogs(java.lang.String string1);
  public boolean hasPermissionToCreateWorklog(java.lang.String string1);
  public boolean hasPermissionToDeleteWorklog(java.lang.String string1);
  public boolean hasPermissionToEditComment(com.atlassian.jira.rpc.soap.beans.RemoteComment remoteComment1);
  public boolean hasPermissionToUpdateWorklog(java.lang.String string1);
  public boolean isProjectRoleNameUnique(java.lang.String string1);
  public java.lang.String login(java.lang.String string1);
  public boolean logout();
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue progressWorkflowAction(java.lang.String string1,java.lang.String string2,com.atlassian.jira.rpc.soap.beans.RemoteFieldValue[] remoteFieldValue3);
  public void refreshCustomFields();
  public void releaseVersion(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion2);
  public void removeActorsFromProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject3,java.lang.String string4);
  public void removeAllRoleActorsByNameAndType(java.lang.String string1,java.lang.String string2);
  public void removeAllRoleActorsByProject(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject1);
  public void removeDefaultActorsFromProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,java.lang.String string3);
  public void removeUserFromGroup(com.atlassian.jira.rpc.soap.beans.RemoteGroup remoteGroup1,com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser2);
  public void setNewProjectAvatar(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  public void setProjectAvatar(java.lang.String string1,long long2);
  public void setUserPassword(com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2);
  public com.atlassian.jira.rpc.soap.beans.RemoteGroup updateGroup(com.atlassian.jira.rpc.soap.beans.RemoteGroup remoteGroup1);
  public com.atlassian.jira.rpc.soap.beans.RemoteIssue updateIssue(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteFieldValue[] remoteFieldValue2);
  public com.atlassian.jira.rpc.soap.beans.RemoteProject updateProject(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject1);
  public void updateProjectRole(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1);
  public com.atlassian.jira.rpc.soap.beans.RemoteUser updateUser(com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser1);
  public void updateWorklogAndAutoAdjustRemainingEstimate(com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog1);
  public void updateWorklogAndRetainRemainingEstimate(com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog1);
  public void updateWorklogWithNewRemainingEstimate(com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog1,java.lang.String string2);
}
