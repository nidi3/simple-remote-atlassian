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

import guru.nidi.atlassian.remote.AtlassianException;

import java.net.URL;
public class DefaultJiraService implements JiraService{
  private final String baseUrl;
  private final String token;
  private final com.atlassian.jira.rpc.soap.jirasoapservice_v2.JiraSoapService service;
  private final guru.nidi.atlassian.remote.jira.rest.JiraRestService jiraRestService;
  private final guru.nidi.atlassian.remote.jira.JiraTasks jiraTasks;
  public DefaultJiraService(String baseUrl, String username, String password){
    this.baseUrl = baseUrl;
    try{
      com.atlassian.jira.rpc.soap.jirasoapservice_v2.JiraSoapServiceServiceLocator locator = new com.atlassian.jira.rpc.soap.jirasoapservice_v2.JiraSoapServiceServiceLocator();
      service = locator.getJirasoapserviceV2(new URL(baseUrl+"/rpc/soap/jirasoapservice-v2"));
      token = service.login(username, password);
      jiraRestService = new guru.nidi.atlassian.remote.jira.rest.JiraRestService(baseUrl, username, password);
      jiraTasks = new guru.nidi.atlassian.remote.jira.JiraTasks(this);
    }catch(Exception e){
      throw new AtlassianException("Error initing DefaultJiraService",e);
    }
  }
  public String getBaseUrl(){ return baseUrl; }
public java.lang.Object executeGet(java.lang.String string0){
    try{
      return jiraRestService.executeGet(string0);
    }catch(Exception e){throw new AtlassianException("Error calling executeGet.",e);}}
public java.lang.Object executePost(java.lang.String string0,java.lang.Object object1){
    try{
      return jiraRestService.executePost(string0,object1);
    }catch(Exception e){throw new AtlassianException("Error calling executePost.",e);}}
public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAllIssuesByJql(java.lang.String string0,java.lang.String string1,java.lang.String string2){
    try{
      return jiraRestService.getAllIssuesByJql(string0,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getAllIssuesByJql.",e);}}
public java.util.List<com.atlassian.jira.rpc.soap.beans.RemoteProject> getAllProjects(){
    try{
      return jiraRestService.getAllProjects();
    }catch(Exception e){throw new AtlassianException("Error calling getAllProjects.",e);}}
public guru.nidi.atlassian.remote.jira.RemoteIssueExt getIssue(java.lang.String string0){
    try{
      return jiraRestService.getIssue(string0);
    }catch(Exception e){throw new AtlassianException("Error calling getIssue.",e);}}
public java.util.Map<java.lang.String, java.lang.Object> getIssue(java.lang.String string0,java.lang.String string1,java.lang.String string2){
    try{
      return jiraRestService.getIssue(string0,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getIssue.",e);}}
public java.util.List<guru.nidi.atlassian.remote.jira.rest.IssueLinkType> getIssueLinkTypes(){
    try{
      return jiraRestService.getIssueLinkTypes();
    }catch(Exception e){throw new AtlassianException("Error calling getIssueLinkTypes.",e);}}
public guru.nidi.atlassian.remote.jira.RemoteIssueExt[] getIssuesByJql(java.lang.String string0,int int1,int int2){
    try{
      return jiraRestService.getIssuesByJql(string0,int1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesByJql.",e);}}
public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getIssuesByJql(java.lang.String string0,int int1,int int2,java.lang.String string3,java.lang.String string4){
    try{
      return jiraRestService.getIssuesByJql(string0,int1,int2,string3,string4);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesByJql.",e);}}
public guru.nidi.atlassian.remote.jira.RemoteIssueExt[] getIssuesFromFilter(java.lang.String string0){
    try{
      return jiraRestService.getIssuesFromFilter(string0);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesFromFilter.",e);}}
public java.util.List<com.atlassian.jira.rpc.soap.beans.RemoteProject> getProjectsByKey(java.lang.String... string0){
    try{
      return jiraRestService.getProjectsByKey(string0);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectsByKey.",e);}}
public void linkIssues(guru.nidi.atlassian.remote.jira.rest.IssueLink issueLink0){
    try{
      jiraRestService.linkIssues(issueLink0);
    }catch(Exception e){throw new AtlassianException("Error calling linkIssues.",e);}}
public java.io.InputStream loadAttachment(com.atlassian.jira.rpc.soap.beans.RemoteAttachment remoteAttachment0){
    try{
      return jiraRestService.loadAttachment(remoteAttachment0);
    }catch(Exception e){throw new AtlassianException("Error calling loadAttachment.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteNamedObject actionByName(java.lang.String string0,java.lang.String string1){
    try{
      return jiraTasks.actionByName(string0,string1);
    }catch(Exception e){throw new AtlassianException("Error calling actionByName.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue createRequirementAndFeature(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,java.lang.String string1,java.lang.String string2,java.lang.String string3){
    try{
      return jiraTasks.createRequirementAndFeature(remoteProject0,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling createRequirementAndFeature.",e);}}
public java.lang.String customFieldByName(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue0,java.lang.String string1){
    try{
      return jiraTasks.customFieldByName(remoteIssue0,string1);
    }catch(Exception e){throw new AtlassianException("Error calling customFieldByName.",e);}}
public java.lang.String fieldNameById(java.lang.String string0){
    try{
      return jiraTasks.fieldNameById(string0);
    }catch(Exception e){throw new AtlassianException("Error calling fieldNameById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssues(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion1,boolean boolean2){
    try{
      return jiraTasks.getIssues(remoteProject0,remoteVersion1,boolean2);
    }catch(Exception e){throw new AtlassianException("Error calling getIssues.",e);}}
public guru.nidi.atlassian.remote.jira.JiraService getService(){
    try{
      return jiraTasks.getService();
    }catch(Exception e){throw new AtlassianException("Error calling getService.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssueType issueTypeById(java.lang.String string0){
    try{
      return jiraTasks.issueTypeById(string0);
    }catch(Exception e){throw new AtlassianException("Error calling issueTypeById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssueType issueTypeByName(java.lang.String string0){
    try{
      return jiraTasks.issueTypeByName(string0);
    }catch(Exception e){throw new AtlassianException("Error calling issueTypeByName.",e);}}
public java.lang.String makeReleaseNotes(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion1,boolean boolean2){
    try{
      return jiraTasks.makeReleaseNotes(remoteProject0,remoteVersion1,boolean2);
    }catch(Exception e){throw new AtlassianException("Error calling makeReleaseNotes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePriority priorityById(java.lang.String string0){
    try{
      return jiraTasks.priorityById(string0);
    }catch(Exception e){throw new AtlassianException("Error calling priorityById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue progressStatusAction(java.lang.String string0,java.lang.String string1,java.lang.String string2){
    try{
      return jiraTasks.progressStatusAction(string0,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling progressStatusAction.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteResolution resolutionById(java.lang.String string0){
    try{
      return jiraTasks.resolutionById(string0);
    }catch(Exception e){throw new AtlassianException("Error calling resolutionById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteSecurityLevel securityLevelByName(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,java.lang.String string1){
    try{
      return jiraTasks.securityLevelByName(remoteProject0,string1);
    }catch(Exception e){throw new AtlassianException("Error calling securityLevelByName.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteStatus statusById(java.lang.String string0){
    try{
      return jiraTasks.statusById(string0);
    }catch(Exception e){throw new AtlassianException("Error calling statusById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteStatus statusByName(java.lang.String string0){
    try{
      return jiraTasks.statusByName(string0);
    }catch(Exception e){throw new AtlassianException("Error calling statusByName.",e);}}
public long timeToResolve(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue0){
    try{
      return jiraTasks.timeToResolve(remoteIssue0);
    }catch(Exception e){throw new AtlassianException("Error calling timeToResolve.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteVersion versionByName(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject0,java.lang.String string1){
    try{
      return jiraTasks.versionByName(remoteProject0,string1);
    }catch(Exception e){throw new AtlassianException("Error calling versionByName.",e);}}
public void addActorsToProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject3,java.lang.String string4){
    try{
      service.addActorsToProjectRole(token,string1,remoteProjectRole2,remoteProject3,string4);
    }catch(Exception e){throw new AtlassianException("Error calling addActorsToProjectRole.",e);}}
public boolean addAttachmentsToIssue(java.lang.String string1,java.lang.String[] string2,byte[][] byte3){
    try{
      return service.addAttachmentsToIssue(token,string1,string2,byte3);
    }catch(Exception e){throw new AtlassianException("Error calling addAttachmentsToIssue.",e);}}
public boolean addBase64EncodedAttachmentsToIssue(java.lang.String string1,java.lang.String[] string2,java.lang.String[] string3){
    try{
      return service.addBase64EncodedAttachmentsToIssue(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling addBase64EncodedAttachmentsToIssue.",e);}}
public void addComment(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteComment remoteComment2){
    try{
      service.addComment(token,string1,remoteComment2);
    }catch(Exception e){throw new AtlassianException("Error calling addComment.",e);}}
public void addDefaultActorsToProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,java.lang.String string3){
    try{
      service.addDefaultActorsToProjectRole(token,string1,remoteProjectRole2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling addDefaultActorsToProjectRole.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme addPermissionTo(com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme remotePermissionScheme1,com.atlassian.jira.rpc.soap.beans.RemotePermission remotePermission2,com.atlassian.jira.rpc.soap.beans.RemoteEntity remoteEntity3){
    try{
      return service.addPermissionTo(token,remotePermissionScheme1,remotePermission2,remoteEntity3);
    }catch(Exception e){throw new AtlassianException("Error calling addPermissionTo.",e);}}
public void addUserToGroup(com.atlassian.jira.rpc.soap.beans.RemoteGroup remoteGroup1,com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser2){
    try{
      service.addUserToGroup(token,remoteGroup1,remoteUser2);
    }catch(Exception e){throw new AtlassianException("Error calling addUserToGroup.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteVersion addVersion(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion2){
    try{
      return service.addVersion(token,string1,remoteVersion2);
    }catch(Exception e){throw new AtlassianException("Error calling addVersion.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteWorklog addWorklogAndAutoAdjustRemainingEstimate(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog2){
    try{
      return service.addWorklogAndAutoAdjustRemainingEstimate(token,string1,remoteWorklog2);
    }catch(Exception e){throw new AtlassianException("Error calling addWorklogAndAutoAdjustRemainingEstimate.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteWorklog addWorklogAndRetainRemainingEstimate(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog2){
    try{
      return service.addWorklogAndRetainRemainingEstimate(token,string1,remoteWorklog2);
    }catch(Exception e){throw new AtlassianException("Error calling addWorklogAndRetainRemainingEstimate.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteWorklog addWorklogWithNewRemainingEstimate(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog2,java.lang.String string3){
    try{
      return service.addWorklogWithNewRemainingEstimate(token,string1,remoteWorklog2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling addWorklogWithNewRemainingEstimate.",e);}}
public void archiveVersion(java.lang.String string1,java.lang.String string2,boolean boolean3){
    try{
      service.archiveVersion(token,string1,string2,boolean3);
    }catch(Exception e){throw new AtlassianException("Error calling archiveVersion.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteGroup createGroup(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser2){
    try{
      return service.createGroup(token,string1,remoteUser2);
    }catch(Exception e){throw new AtlassianException("Error calling createGroup.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssue(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1){
    try{
      return service.createIssue(token,remoteIssue1);
    }catch(Exception e){throw new AtlassianException("Error calling createIssue.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssueWithParent(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1,java.lang.String string2){
    try{
      return service.createIssueWithParent(token,remoteIssue1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling createIssueWithParent.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssueWithParentWithSecurityLevel(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1,java.lang.String string2,long long3){
    try{
      return service.createIssueWithParentWithSecurityLevel(token,remoteIssue1,string2,long3);
    }catch(Exception e){throw new AtlassianException("Error calling createIssueWithParentWithSecurityLevel.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue createIssueWithSecurityLevel(com.atlassian.jira.rpc.soap.beans.RemoteIssue remoteIssue1,long long2){
    try{
      return service.createIssueWithSecurityLevel(token,remoteIssue1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling createIssueWithSecurityLevel.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme createPermissionScheme(java.lang.String string1,java.lang.String string2){
    try{
      return service.createPermissionScheme(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling createPermissionScheme.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject createProject(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4,java.lang.String string5,com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme remotePermissionScheme6,com.atlassian.jira.rpc.soap.beans.RemoteScheme remoteScheme7,com.atlassian.jira.rpc.soap.beans.RemoteScheme remoteScheme8){
    try{
      return service.createProject(token,string1,string2,string3,string4,string5,remotePermissionScheme6,remoteScheme7,remoteScheme8);
    }catch(Exception e){throw new AtlassianException("Error calling createProject.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject createProjectFromObject(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject1){
    try{
      return service.createProjectFromObject(token,remoteProject1);
    }catch(Exception e){throw new AtlassianException("Error calling createProjectFromObject.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProjectRole createProjectRole(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1){
    try{
      return service.createProjectRole(token,remoteProjectRole1);
    }catch(Exception e){throw new AtlassianException("Error calling createProjectRole.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteUser createUser(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4){
    try{
      return service.createUser(token,string1,string2,string3,string4);
    }catch(Exception e){throw new AtlassianException("Error calling createUser.",e);}}
public void deleteGroup(java.lang.String string1,java.lang.String string2){
    try{
      service.deleteGroup(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling deleteGroup.",e);}}
public void deleteIssue(java.lang.String string1){
    try{
      service.deleteIssue(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deleteIssue.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme deletePermissionFrom(com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme remotePermissionScheme1,com.atlassian.jira.rpc.soap.beans.RemotePermission remotePermission2,com.atlassian.jira.rpc.soap.beans.RemoteEntity remoteEntity3){
    try{
      return service.deletePermissionFrom(token,remotePermissionScheme1,remotePermission2,remoteEntity3);
    }catch(Exception e){throw new AtlassianException("Error calling deletePermissionFrom.",e);}}
public void deletePermissionScheme(java.lang.String string1){
    try{
      service.deletePermissionScheme(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deletePermissionScheme.",e);}}
public void deleteProject(java.lang.String string1){
    try{
      service.deleteProject(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deleteProject.",e);}}
public void deleteProjectAvatar(long long1){
    try{
      service.deleteProjectAvatar(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling deleteProjectAvatar.",e);}}
public void deleteProjectRole(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1,boolean boolean2){
    try{
      service.deleteProjectRole(token,remoteProjectRole1,boolean2);
    }catch(Exception e){throw new AtlassianException("Error calling deleteProjectRole.",e);}}
public void deleteUser(java.lang.String string1){
    try{
      service.deleteUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deleteUser.",e);}}
public void deleteWorklogAndAutoAdjustRemainingEstimate(java.lang.String string1){
    try{
      service.deleteWorklogAndAutoAdjustRemainingEstimate(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deleteWorklogAndAutoAdjustRemainingEstimate.",e);}}
public void deleteWorklogAndRetainRemainingEstimate(java.lang.String string1){
    try{
      service.deleteWorklogAndRetainRemainingEstimate(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deleteWorklogAndRetainRemainingEstimate.",e);}}
public void deleteWorklogWithNewRemainingEstimate(java.lang.String string1,java.lang.String string2){
    try{
      service.deleteWorklogWithNewRemainingEstimate(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling deleteWorklogWithNewRemainingEstimate.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteComment editComment(com.atlassian.jira.rpc.soap.beans.RemoteComment remoteComment1){
    try{
      return service.editComment(token,remoteComment1);
    }catch(Exception e){throw new AtlassianException("Error calling editComment.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePermission[] getAllPermissions(){
    try{
      return service.getAllPermissions(token);
    }catch(Exception e){throw new AtlassianException("Error calling getAllPermissions.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getAssociatedNotificationSchemes(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1){
    try{
      return service.getAssociatedNotificationSchemes(token,remoteProjectRole1);
    }catch(Exception e){throw new AtlassianException("Error calling getAssociatedNotificationSchemes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getAssociatedPermissionSchemes(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1){
    try{
      return service.getAssociatedPermissionSchemes(token,remoteProjectRole1);
    }catch(Exception e){throw new AtlassianException("Error calling getAssociatedPermissionSchemes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteAttachment[] getAttachmentsFromIssue(java.lang.String string1){
    try{
      return service.getAttachmentsFromIssue(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getAttachmentsFromIssue.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteNamedObject[] getAvailableActions(java.lang.String string1){
    try{
      return service.getAvailableActions(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getAvailableActions.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteComment getComment(long long1){
    try{
      return service.getComment(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getComment.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteComment[] getComments(java.lang.String string1){
    try{
      return service.getComments(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getComments.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteComponent[] getComponents(java.lang.String string1){
    try{
      return service.getComponents(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getComponents.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteConfiguration getConfiguration(){
    try{
      return service.getConfiguration(token);
    }catch(Exception e){throw new AtlassianException("Error calling getConfiguration.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteField[] getCustomFields(){
    try{
      return service.getCustomFields(token);
    }catch(Exception e){throw new AtlassianException("Error calling getCustomFields.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteRoleActors getDefaultRoleActors(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1){
    try{
      return service.getDefaultRoleActors(token,remoteProjectRole1);
    }catch(Exception e){throw new AtlassianException("Error calling getDefaultRoleActors.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteFilter[] getFavouriteFilters(){
    try{
      return service.getFavouriteFilters(token);
    }catch(Exception e){throw new AtlassianException("Error calling getFavouriteFilters.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteField[] getFieldsForAction(java.lang.String string1,java.lang.String string2){
    try{
      return service.getFieldsForAction(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getFieldsForAction.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteField[] getFieldsForCreate(java.lang.String string1,long long2){
    try{
      return service.getFieldsForCreate(token,string1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling getFieldsForCreate.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteField[] getFieldsForEdit(java.lang.String string1){
    try{
      return service.getFieldsForEdit(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getFieldsForEdit.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteGroup getGroup(java.lang.String string1){
    try{
      return service.getGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getGroup.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue getIssueById(java.lang.String string1){
    try{
      return service.getIssueById(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getIssueById.",e);}}
public long getIssueCountForFilter(java.lang.String string1){
    try{
      return service.getIssueCountForFilter(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getIssueCountForFilter.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getIssueTypes(){
    try{
      return service.getIssueTypes(token);
    }catch(Exception e){throw new AtlassianException("Error calling getIssueTypes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getIssueTypesForProject(java.lang.String string1){
    try{
      return service.getIssueTypesForProject(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getIssueTypesForProject.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromFilterWithLimit(java.lang.String string1,int int2,int int3){
    try{
      return service.getIssuesFromFilterWithLimit(token,string1,int2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesFromFilterWithLimit.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromJqlSearch(java.lang.String string1,int int2){
    try{
      return service.getIssuesFromJqlSearch(token,string1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesFromJqlSearch.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromTextSearch(java.lang.String string1){
    try{
      return service.getIssuesFromTextSearch(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesFromTextSearch.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromTextSearchWithLimit(java.lang.String string1,int int2,int int3){
    try{
      return service.getIssuesFromTextSearchWithLimit(token,string1,int2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesFromTextSearchWithLimit.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue[] getIssuesFromTextSearchWithProject(java.lang.String[] string1,java.lang.String string2,int int3){
    try{
      return service.getIssuesFromTextSearchWithProject(token,string1,string2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getIssuesFromTextSearchWithProject.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getNotificationSchemes(){
    try{
      return service.getNotificationSchemes(token);
    }catch(Exception e){throw new AtlassianException("Error calling getNotificationSchemes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePermissionScheme[] getPermissionSchemes(){
    try{
      return service.getPermissionSchemes(token);
    }catch(Exception e){throw new AtlassianException("Error calling getPermissionSchemes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemotePriority[] getPriorities(){
    try{
      return service.getPriorities(token);
    }catch(Exception e){throw new AtlassianException("Error calling getPriorities.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteAvatar getProjectAvatar(java.lang.String string1){
    try{
      return service.getProjectAvatar(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectAvatar.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteAvatar[] getProjectAvatars(java.lang.String string1,boolean boolean2){
    try{
      return service.getProjectAvatars(token,string1,boolean2);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectAvatars.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject getProjectById(long long1){
    try{
      return service.getProjectById(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject getProjectByKey(java.lang.String string1){
    try{
      return service.getProjectByKey(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectByKey.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProjectRole getProjectRole(long long1){
    try{
      return service.getProjectRole(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectRole.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProjectRoleActors getProjectRoleActors(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1,com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject2){
    try{
      return service.getProjectRoleActors(token,remoteProjectRole1,remoteProject2);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectRoleActors.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProjectRole[] getProjectRoles(){
    try{
      return service.getProjectRoles(token);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectRoles.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject getProjectWithSchemesById(long long1){
    try{
      return service.getProjectWithSchemesById(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectWithSchemesById.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject[] getProjectsNoSchemes(){
    try{
      return service.getProjectsNoSchemes(token);
    }catch(Exception e){throw new AtlassianException("Error calling getProjectsNoSchemes.",e);}}
public java.util.Calendar getResolutionDateById(long long1){
    try{
      return service.getResolutionDateById(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getResolutionDateById.",e);}}
public java.util.Calendar getResolutionDateByKey(java.lang.String string1){
    try{
      return service.getResolutionDateByKey(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getResolutionDateByKey.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteResolution[] getResolutions(){
    try{
      return service.getResolutions(token);
    }catch(Exception e){throw new AtlassianException("Error calling getResolutions.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteFilter[] getSavedFilters(){
    try{
      return service.getSavedFilters(token);
    }catch(Exception e){throw new AtlassianException("Error calling getSavedFilters.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteSecurityLevel getSecurityLevel(java.lang.String string1){
    try{
      return service.getSecurityLevel(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSecurityLevel.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteSecurityLevel[] getSecurityLevels(java.lang.String string1){
    try{
      return service.getSecurityLevels(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSecurityLevels.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteScheme[] getSecuritySchemes(){
    try{
      return service.getSecuritySchemes(token);
    }catch(Exception e){throw new AtlassianException("Error calling getSecuritySchemes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteServerInfo getServerInfo(){
    try{
      return service.getServerInfo(token);
    }catch(Exception e){throw new AtlassianException("Error calling getServerInfo.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteStatus[] getStatuses(){
    try{
      return service.getStatuses(token);
    }catch(Exception e){throw new AtlassianException("Error calling getStatuses.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getSubTaskIssueTypes(){
    try{
      return service.getSubTaskIssueTypes(token);
    }catch(Exception e){throw new AtlassianException("Error calling getSubTaskIssueTypes.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssueType[] getSubTaskIssueTypesForProject(java.lang.String string1){
    try{
      return service.getSubTaskIssueTypesForProject(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSubTaskIssueTypesForProject.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteUser getUser(java.lang.String string1){
    try{
      return service.getUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getUser.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteVersion[] getVersions(java.lang.String string1){
    try{
      return service.getVersions(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getVersions.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteWorklog[] getWorklogs(java.lang.String string1){
    try{
      return service.getWorklogs(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getWorklogs.",e);}}
public boolean hasPermissionToCreateWorklog(java.lang.String string1){
    try{
      return service.hasPermissionToCreateWorklog(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling hasPermissionToCreateWorklog.",e);}}
public boolean hasPermissionToDeleteWorklog(java.lang.String string1){
    try{
      return service.hasPermissionToDeleteWorklog(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling hasPermissionToDeleteWorklog.",e);}}
public boolean hasPermissionToEditComment(com.atlassian.jira.rpc.soap.beans.RemoteComment remoteComment1){
    try{
      return service.hasPermissionToEditComment(token,remoteComment1);
    }catch(Exception e){throw new AtlassianException("Error calling hasPermissionToEditComment.",e);}}
public boolean hasPermissionToUpdateWorklog(java.lang.String string1){
    try{
      return service.hasPermissionToUpdateWorklog(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling hasPermissionToUpdateWorklog.",e);}}
public boolean isProjectRoleNameUnique(java.lang.String string1){
    try{
      return service.isProjectRoleNameUnique(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling isProjectRoleNameUnique.",e);}}
public java.lang.String login(java.lang.String string1){
    try{
      return service.login(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling login.",e);}}
public boolean logout(){
    try{
      return service.logout(token);
    }catch(Exception e){throw new AtlassianException("Error calling logout.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue progressWorkflowAction(java.lang.String string1,java.lang.String string2,com.atlassian.jira.rpc.soap.beans.RemoteFieldValue[] remoteFieldValue3){
    try{
      return service.progressWorkflowAction(token,string1,string2,remoteFieldValue3);
    }catch(Exception e){throw new AtlassianException("Error calling progressWorkflowAction.",e);}}
public void refreshCustomFields(){
    try{
      service.refreshCustomFields(token);
    }catch(Exception e){throw new AtlassianException("Error calling refreshCustomFields.",e);}}
public void releaseVersion(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteVersion remoteVersion2){
    try{
      service.releaseVersion(token,string1,remoteVersion2);
    }catch(Exception e){throw new AtlassianException("Error calling releaseVersion.",e);}}
public void removeActorsFromProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject3,java.lang.String string4){
    try{
      service.removeActorsFromProjectRole(token,string1,remoteProjectRole2,remoteProject3,string4);
    }catch(Exception e){throw new AtlassianException("Error calling removeActorsFromProjectRole.",e);}}
public void removeAllRoleActorsByNameAndType(java.lang.String string1,java.lang.String string2){
    try{
      service.removeAllRoleActorsByNameAndType(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeAllRoleActorsByNameAndType.",e);}}
public void removeAllRoleActorsByProject(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject1){
    try{
      service.removeAllRoleActorsByProject(token,remoteProject1);
    }catch(Exception e){throw new AtlassianException("Error calling removeAllRoleActorsByProject.",e);}}
public void removeDefaultActorsFromProjectRole(java.lang.String[] string1,com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole2,java.lang.String string3){
    try{
      service.removeDefaultActorsFromProjectRole(token,string1,remoteProjectRole2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling removeDefaultActorsFromProjectRole.",e);}}
public void removeUserFromGroup(com.atlassian.jira.rpc.soap.beans.RemoteGroup remoteGroup1,com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser2){
    try{
      service.removeUserFromGroup(token,remoteGroup1,remoteUser2);
    }catch(Exception e){throw new AtlassianException("Error calling removeUserFromGroup.",e);}}
public void setNewProjectAvatar(java.lang.String string1,java.lang.String string2,java.lang.String string3){
    try{
      service.setNewProjectAvatar(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling setNewProjectAvatar.",e);}}
public void setProjectAvatar(java.lang.String string1,long long2){
    try{
      service.setProjectAvatar(token,string1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling setProjectAvatar.",e);}}
public void setUserPassword(com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2){
    try{
      service.setUserPassword(token,remoteUser1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling setUserPassword.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteGroup updateGroup(com.atlassian.jira.rpc.soap.beans.RemoteGroup remoteGroup1){
    try{
      return service.updateGroup(token,remoteGroup1);
    }catch(Exception e){throw new AtlassianException("Error calling updateGroup.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteIssue updateIssue(java.lang.String string1,com.atlassian.jira.rpc.soap.beans.RemoteFieldValue[] remoteFieldValue2){
    try{
      return service.updateIssue(token,string1,remoteFieldValue2);
    }catch(Exception e){throw new AtlassianException("Error calling updateIssue.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteProject updateProject(com.atlassian.jira.rpc.soap.beans.RemoteProject remoteProject1){
    try{
      return service.updateProject(token,remoteProject1);
    }catch(Exception e){throw new AtlassianException("Error calling updateProject.",e);}}
public void updateProjectRole(com.atlassian.jira.rpc.soap.beans.RemoteProjectRole remoteProjectRole1){
    try{
      service.updateProjectRole(token,remoteProjectRole1);
    }catch(Exception e){throw new AtlassianException("Error calling updateProjectRole.",e);}}
public com.atlassian.jira.rpc.soap.beans.RemoteUser updateUser(com.atlassian.jira.rpc.soap.beans.RemoteUser remoteUser1){
    try{
      return service.updateUser(token,remoteUser1);
    }catch(Exception e){throw new AtlassianException("Error calling updateUser.",e);}}
public void updateWorklogAndAutoAdjustRemainingEstimate(com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog1){
    try{
      service.updateWorklogAndAutoAdjustRemainingEstimate(token,remoteWorklog1);
    }catch(Exception e){throw new AtlassianException("Error calling updateWorklogAndAutoAdjustRemainingEstimate.",e);}}
public void updateWorklogAndRetainRemainingEstimate(com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog1){
    try{
      service.updateWorklogAndRetainRemainingEstimate(token,remoteWorklog1);
    }catch(Exception e){throw new AtlassianException("Error calling updateWorklogAndRetainRemainingEstimate.",e);}}
public void updateWorklogWithNewRemainingEstimate(com.atlassian.jira.rpc.soap.beans.RemoteWorklog remoteWorklog1,java.lang.String string2){
    try{
      service.updateWorklogWithNewRemainingEstimate(token,remoteWorklog1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling updateWorklogWithNewRemainingEstimate.",e);}}
}
