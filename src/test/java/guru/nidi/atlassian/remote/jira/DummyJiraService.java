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

import com.atlassian.jira.rpc.soap.beans.*;
import guru.nidi.atlassian.remote.jira.rest.IssueLink;
import guru.nidi.atlassian.remote.jira.rest.IssueLinkType;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DummyJiraService implements JiraService {

    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public void addActorsToProjectRole(String[] string1, RemoteProjectRole remoteProjectRole2, RemoteProject remoteProject3, String string4) {

    }

    @Override
    public boolean addAttachmentsToIssue(String string1, String[] string2, byte[][] byte3) {
        return false;
    }

    @Override
    public boolean addBase64EncodedAttachmentsToIssue(String string1, String[] string2, String[] string3) {
        return false;
    }

    @Override
    public void addComment(String string1, RemoteComment remoteComment2) {

    }

    @Override
    public void addDefaultActorsToProjectRole(String[] string1, RemoteProjectRole remoteProjectRole2, String string3) {

    }

    @Override
    public RemotePermissionScheme addPermissionTo(RemotePermissionScheme remotePermissionScheme1, RemotePermission remotePermission2, RemoteEntity remoteEntity3) {
        return null;
    }

    @Override
    public void addUserToGroup(RemoteGroup remoteGroup1, RemoteUser remoteUser2) {

    }

    @Override
    public RemoteVersion addVersion(String string1, RemoteVersion remoteVersion2) {
        return null;
    }

    @Override
    public RemoteWorklog addWorklogAndAutoAdjustRemainingEstimate(String string1, RemoteWorklog remoteWorklog2) {
        return null;
    }

    @Override
    public RemoteWorklog addWorklogAndRetainRemainingEstimate(String string1, RemoteWorklog remoteWorklog2) {
        return null;
    }

    @Override
    public RemoteWorklog addWorklogWithNewRemainingEstimate(String string1, RemoteWorklog remoteWorklog2, String string3) {
        return null;
    }

    @Override
    public void archiveVersion(String string1, String string2, boolean boolean3) {

    }

    @Override
    public RemoteGroup createGroup(String string1, RemoteUser remoteUser2) {
        return null;
    }

    @Override
    public RemoteIssue createIssue(RemoteIssue remoteIssue1) {
        return null;
    }

    @Override
    public RemoteIssue createIssueWithParent(RemoteIssue remoteIssue1, String string2) {
        return null;
    }

    @Override
    public RemoteIssue createIssueWithParentWithSecurityLevel(RemoteIssue remoteIssue1, String string2, long long3) {
        return null;
    }

    @Override
    public RemoteIssue createIssueWithSecurityLevel(RemoteIssue remoteIssue1, long long2) {
        return null;
    }

    @Override
    public RemotePermissionScheme createPermissionScheme(String string1, String string2) {
        return null;
    }

    @Override
    public RemoteProject createProject(String string1, String string2, String string3, String string4, String string5, RemotePermissionScheme remotePermissionScheme6, RemoteScheme remoteScheme7, RemoteScheme remoteScheme8) {
        return null;
    }

    @Override
    public RemoteProject createProjectFromObject(RemoteProject remoteProject1) {
        return null;
    }


    @Override
    public RemoteUser createUser(String string1, String string2, String string3, String string4) {
        return null;
    }

    @Override
    public void deleteGroup(String string1, String string2) {

    }

    @Override
    public void deleteIssue(String string1) {

    }

    @Override
    public RemotePermissionScheme deletePermissionFrom(RemotePermissionScheme remotePermissionScheme1, RemotePermission remotePermission2, RemoteEntity remoteEntity3) {
        return null;
    }

    @Override
    public void deletePermissionScheme(String string1) {

    }

    @Override
    public void deleteProject(String string1) {

    }

    @Override
    public void deleteProjectAvatar(long long1) {

    }

    @Override
    public void deleteProjectRole(RemoteProjectRole remoteProjectRole1, boolean boolean2) {

    }

    @Override
    public void deleteUser(String string1) {

    }

    @Override
    public void deleteWorklogAndAutoAdjustRemainingEstimate(String string1) {

    }

    @Override
    public void deleteWorklogAndRetainRemainingEstimate(String string1) {

    }

    @Override
    public void deleteWorklogWithNewRemainingEstimate(String string1, String string2) {

    }

    @Override
    public RemoteComment editComment(RemoteComment remoteComment1) {
        return null;
    }

    @Override
    public RemotePermission[] getAllPermissions() {
        return new RemotePermission[0];
    }

    @Override
    public RemoteScheme[] getAssociatedNotificationSchemes(RemoteProjectRole remoteProjectRole1) {
        return new RemoteScheme[0];
    }

    @Override
    public RemoteScheme[] getAssociatedPermissionSchemes(RemoteProjectRole remoteProjectRole1) {
        return new RemoteScheme[0];
    }

    @Override
    public RemoteAttachment[] getAttachmentsFromIssue(String string1) {
        return new RemoteAttachment[0];
    }

    @Override
    public RemoteNamedObject[] getAvailableActions(String string1) {
        return new RemoteNamedObject[0];
    }

    @Override
    public RemoteComment getComment(long long1) {
        return null;
    }

    @Override
    public RemoteComment[] getComments(String string1) {
        return new RemoteComment[0];
    }

    @Override
    public RemoteComponent[] getComponents(String string1) {
        return new RemoteComponent[0];
    }

    @Override
    public RemoteConfiguration getConfiguration() {
        return null;
    }

    @Override
    public RemoteField[] getCustomFields() {
        return new RemoteField[0];
    }

    @Override
    public RemoteRoleActors getDefaultRoleActors(RemoteProjectRole remoteProjectRole1) {
        return null;
    }

    @Override
    public RemoteFilter[] getFavouriteFilters() {
        return new RemoteFilter[0];
    }

    @Override
    public RemoteField[] getFieldsForAction(String string1, String string2) {
        return new RemoteField[0];
    }

    @Override
    public RemoteField[] getFieldsForCreate(String string1, long long2) {
        return new RemoteField[0];
    }

    @Override
    public RemoteField[] getFieldsForEdit(String string1) {
        return new RemoteField[0];
    }

    @Override
    public RemoteGroup getGroup(String string1) {
        return null;
    }

    @Override
    public RemoteIssueExt getIssue(String string1) {
        return null;
    }

    @Override
    public Map<String, Object> getIssue(String string0, String string1, String string2) {
        return null;
    }

    @Override
    public RemoteIssue getIssueById(String string1) {
        return null;
    }

    @Override
    public long getIssueCountForFilter(String string1) {
        return 0;
    }

    @Override
    public RemoteIssueType[] getIssueTypes() {
        return new RemoteIssueType[0];
    }

    @Override
    public RemoteIssueType[] getIssueTypesForProject(String string1) {
        return new RemoteIssueType[0];
    }

    @Override
    public RemoteIssueExt[] getIssuesFromFilter(String string1) {
        return new RemoteIssueExt[0];
    }

    @Override
    public RemoteIssue[] getIssuesFromFilterWithLimit(String string1, int int2, int int3) {
        return new RemoteIssue[0];
    }

    @Override
    public RemoteIssue[] getIssuesFromJqlSearch(String string1, int int2) {
        return new RemoteIssue[0];
    }

    @Override
    public RemoteIssue[] getIssuesFromTextSearch(String string1) {
        return new RemoteIssue[0];
    }

    @Override
    public RemoteIssue[] getIssuesFromTextSearchWithLimit(String string1, int int2, int int3) {
        return new RemoteIssue[0];
    }

    @Override
    public RemoteIssue[] getIssuesFromTextSearchWithProject(String[] string1, String string2, int int3) {
        return new RemoteIssue[0];
    }

    @Override
    public RemoteScheme[] getNotificationSchemes() {
        return new RemoteScheme[0];
    }

    @Override
    public RemotePermissionScheme[] getPermissionSchemes() {
        return new RemotePermissionScheme[0];
    }

    @Override
    public RemotePriority[] getPriorities() {
        return new RemotePriority[0];
    }

    @Override
    public RemoteAvatar getProjectAvatar(String string1) {
        return null;
    }

    @Override
    public RemoteAvatar[] getProjectAvatars(String string1, boolean boolean2) {
        return new RemoteAvatar[0];
    }

    @Override
    public RemoteProject getProjectById(long long1) {
        return null;
    }

    @Override
    public RemoteProject getProjectByKey(String string1) {
        return null;
    }

    @Override
    public RemoteProjectRole getProjectRole(long long1) {
        return null;
    }

    @Override
    public RemoteProjectRoleActors getProjectRoleActors(RemoteProjectRole remoteProjectRole1, RemoteProject remoteProject2) {
        return null;
    }

    @Override
    public RemoteProjectRole[] getProjectRoles() {
        return new RemoteProjectRole[0];
    }

    @Override
    public RemoteProject getProjectWithSchemesById(long long1) {
        return null;
    }

    @Override
    public RemoteProject[] getProjectsNoSchemes() {
        return new RemoteProject[0];
    }

    @Override
    public Calendar getResolutionDateById(long long1) {
        return null;
    }

    @Override
    public Calendar getResolutionDateByKey(String string1) {
        return null;
    }

    @Override
    public RemoteResolution[] getResolutions() {
        return new RemoteResolution[0];
    }

    @Override
    public RemoteFilter[] getSavedFilters() {
        return new RemoteFilter[0];
    }

    @Override
    public RemoteSecurityLevel getSecurityLevel(String string1) {
        return null;
    }

    @Override
    public RemoteSecurityLevel[] getSecurityLevels(String string1) {
        return new RemoteSecurityLevel[0];
    }

    @Override
    public RemoteScheme[] getSecuritySchemes() {
        return new RemoteScheme[0];
    }

    @Override
    public RemoteServerInfo getServerInfo() {
        return null;
    }

    @Override
    public RemoteStatus[] getStatuses() {
        return new RemoteStatus[0];
    }

    @Override
    public RemoteIssueType[] getSubTaskIssueTypes() {
        return new RemoteIssueType[0];
    }

    @Override
    public RemoteIssueType[] getSubTaskIssueTypesForProject(String string1) {
        return new RemoteIssueType[0];
    }

    @Override
    public RemoteUser getUser(String string1) {
        return null;
    }

    @Override
    public RemoteVersion[] getVersions(String string1) {
        return new RemoteVersion[0];
    }

    @Override
    public RemoteWorklog[] getWorklogs(String string1) {
        return new RemoteWorklog[0];
    }

    @Override
    public boolean hasPermissionToCreateWorklog(String string1) {
        return false;
    }

    @Override
    public boolean hasPermissionToDeleteWorklog(String string1) {
        return false;
    }

    @Override
    public boolean hasPermissionToEditComment(RemoteComment remoteComment1) {
        return false;
    }

    @Override
    public boolean hasPermissionToUpdateWorklog(String string1) {
        return false;
    }

    @Override
    public boolean isProjectRoleNameUnique(String string1) {
        return false;
    }

    @Override
    public String login(String string1) {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public RemoteIssue progressWorkflowAction(String string1, String string2, RemoteFieldValue[] remoteFieldValue3) {
        return null;
    }

    @Override
    public void refreshCustomFields() {

    }

    @Override
    public void releaseVersion(String string1, RemoteVersion remoteVersion2) {

    }

    @Override
    public void removeActorsFromProjectRole(String[] string1, RemoteProjectRole remoteProjectRole2, RemoteProject remoteProject3, String string4) {

    }

    @Override
    public void removeAllRoleActorsByNameAndType(String string1, String string2) {

    }

    @Override
    public void removeAllRoleActorsByProject(RemoteProject remoteProject1) {

    }

    @Override
    public void removeDefaultActorsFromProjectRole(String[] string1, RemoteProjectRole remoteProjectRole2, String string3) {

    }

    @Override
    public void removeUserFromGroup(RemoteGroup remoteGroup1, RemoteUser remoteUser2) {

    }

    @Override
    public void setNewProjectAvatar(String string1, String string2, String string3) {

    }

    @Override
    public void setProjectAvatar(String string1, long long2) {

    }

    @Override
    public void setUserPassword(RemoteUser remoteUser1, String string2) {

    }

    @Override
    public RemoteGroup updateGroup(RemoteGroup remoteGroup1) {
        return null;
    }

    @Override
    public RemoteIssue updateIssue(String string1, RemoteFieldValue[] remoteFieldValue2) {
        return null;
    }

    @Override
    public RemoteProject updateProject(RemoteProject remoteProject1) {
        return null;
    }

    @Override
    public void updateProjectRole(RemoteProjectRole remoteProjectRole1) {

    }

    @Override
    public RemoteUser updateUser(RemoteUser remoteUser1) {
        return null;
    }

    @Override
    public void updateWorklogAndAutoAdjustRemainingEstimate(RemoteWorklog remoteWorklog1) {

    }

    @Override
    public void updateWorklogAndRetainRemainingEstimate(RemoteWorklog remoteWorklog1) {

    }

    @Override
    public void updateWorklogWithNewRemainingEstimate(RemoteWorklog remoteWorklog1, String string2) {

    }

    @Override
    public Object executeGet(String string0) {
        return null;
    }

    @Override
    public Object executePost(String string0, Object object1) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getAllIssuesByJql(String string0, String string1, String string2) {
        return null;
    }

    @Override
    public List<RemoteProject> getAllProjects() {
        return null;
    }

    @Override
    public List<IssueLinkType> getIssueLinkTypes() {
        return null;
    }

    @Override
    public RemoteProjectRole createProjectRole(RemoteProjectRole remoteProjectRole1) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RemoteIssueExt[] getIssuesByJql(String string0, int int1, int int2) {
        return new RemoteIssueExt[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Map<String, Object>> getIssuesByJql(String string0, int int1, int int2, String string3, String string4) {
        return null;
    }

    @Override
    public List<RemoteProject> getProjectsByKey(String... string0) {
        return null;
    }

    @Override
    public void linkIssues(IssueLink issueLink0) {

    }

    @Override
    public InputStream loadAttachment(RemoteAttachment remoteAttachment0) {
        return null;
    }

    @Override
    public RemoteNamedObject actionByName(String string0, String string1) {
        return null;
    }

    @Override
    public RemoteIssue createRequirementAndFeature(RemoteProject remoteProject0, String string1, String string2, String s) {
        return null;
    }

    @Override
    public String customFieldByName(RemoteIssue remoteIssue0, String string1) {
        return null;
    }

    @Override
    public String fieldNameById(String string0) {
        return null;
    }

    @Override
    public RemoteIssue[] getIssues(RemoteProject remoteProject0, RemoteVersion remoteVersion1, boolean boolean2) {
        return new RemoteIssue[0];
    }

    @Override
    public JiraService getService() {
        return null;
    }

    @Override
    public RemoteIssueType issueTypeById(String string0) {
        return null;
    }

    @Override
    public RemoteIssueType issueTypeByName(String string0) {
        return null;
    }

    @Override
    public String makeReleaseNotes(RemoteProject remoteProject0, RemoteVersion remoteVersion1, boolean boolean2) {
        return null;
    }

    @Override
    public RemotePriority priorityById(String string0) {
        return null;
    }

    @Override
    public RemoteIssue progressStatusAction(String string0, String string1, String string2) {
        return null;
    }

    @Override
    public RemoteResolution resolutionById(String string0) {
        return null;
    }

    @Override
    public RemoteSecurityLevel securityLevelByName(RemoteProject remoteProject0, String string1) {
        return null;
    }

    @Override
    public RemoteStatus statusById(String string0) {
        return null;
    }

    @Override
    public RemoteStatus statusByName(String string0) {
        return null;
    }

    @Override
    public long timeToResolve(RemoteIssue remoteIssue0) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RemoteVersion versionByName(RemoteProject remoteProject0, String string1) {
        return null;
    }
}