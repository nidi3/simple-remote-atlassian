package guru.nidi.atlassian.remote.confluence;

import guru.nidi.atlassian.remote.AtlassianException;

import java.net.URL;
public class DefaultConfluenceService implements ConfluenceService{
  private final String baseUrl;
  private final String token;
  private final com.atlassian.confluence.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapService service;
  private final guru.nidi.atlassian.remote.confluence.ConfluenceTasks confluenceTasks;
  public DefaultConfluenceService(String baseUrl, String username, String password){
    this.baseUrl = baseUrl;
    try{
      com.atlassian.confluence.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapServiceServiceLocator locator = new com.atlassian.confluence.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapServiceServiceLocator();
      service = locator.getConfluenceserviceV2(new URL(baseUrl+"/rpc/soap-axis/confluenceservice-v2"));
      token = service.login(username, password);
      confluenceTasks = new guru.nidi.atlassian.remote.confluence.ConfluenceTasks(this);
    }catch(Exception e){
      throw new AtlassianException("Error initing DefaultConfluenceService",e);
    }
  }
  public String getBaseUrl(){ return baseUrl; }
public com.atlassian.confluence.rpc.soap.beans.RemotePage getOrCreatePage(long long0,java.lang.String string1){
    try{
      return confluenceTasks.getOrCreatePage(long0,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getOrCreatePage.",e);}}
public guru.nidi.atlassian.remote.confluence.ConfluenceService getService(){
    try{
      return confluenceTasks.getService();
    }catch(Exception e){throw new AtlassianException("Error calling getService.",e);}}
public boolean addAnonymousPermissionToSpace(java.lang.String string1,java.lang.String string2){
    try{
      return service.addAnonymousPermissionToSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addAnonymousPermissionToSpace.",e);}}
public boolean addAnonymousPermissionsToSpace(java.lang.String[] string1,java.lang.String string2){
    try{
      return service.addAnonymousPermissionsToSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addAnonymousPermissionsToSpace.",e);}}
public boolean addAnonymousUsePermission(){
    try{
      return service.addAnonymousUsePermission(token);
    }catch(Exception e){throw new AtlassianException("Error calling addAnonymousUsePermission.",e);}}
public boolean addAnonymousViewUserProfilePermission(){
    try{
      return service.addAnonymousViewUserProfilePermission(token);
    }catch(Exception e){throw new AtlassianException("Error calling addAnonymousViewUserProfilePermission.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment addAttachment(com.atlassian.confluence.rpc.soap.beans.RemoteAttachment remoteAttachment1,byte[] byte2){
    try{
      return service.addAttachment(token,remoteAttachment1,byte2);
    }catch(Exception e){throw new AtlassianException("Error calling addAttachment.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment addAttachment(long long1,com.atlassian.confluence.rpc.soap.beans.RemoteAttachment remoteAttachment2,byte[] byte3){
    try{
      return service.addAttachment(token,long1,remoteAttachment2,byte3);
    }catch(Exception e){throw new AtlassianException("Error calling addAttachment.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteComment addComment(com.atlassian.confluence.rpc.soap.beans.RemoteComment remoteComment1){
    try{
      return service.addComment(token,remoteComment1);
    }catch(Exception e){throw new AtlassianException("Error calling addComment.",e);}}
public boolean addGlobalPermission(java.lang.String string1,java.lang.String string2){
    try{
      return service.addGlobalPermission(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addGlobalPermission.",e);}}
public boolean addGlobalPermissions(java.lang.String[] string1,java.lang.String string2){
    try{
      return service.addGlobalPermissions(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addGlobalPermissions.",e);}}
public boolean addGroup(java.lang.String string1){
    try{
      return service.addGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling addGroup.",e);}}
public boolean addLabelById(long long1,long long2){
    try{
      return service.addLabelById(token,long1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling addLabelById.",e);}}
public boolean addLabelByName(java.lang.String string1,long long2){
    try{
      return service.addLabelByName(token,string1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling addLabelByName.",e);}}
public boolean addLabelByNameToSpace(java.lang.String string1,java.lang.String string2){
    try{
      return service.addLabelByNameToSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addLabelByNameToSpace.",e);}}
public boolean addLabelByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1,long long2){
    try{
      return service.addLabelByObject(token,remoteLabel1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling addLabelByObject.",e);}}
public boolean addPermissionToSpace(java.lang.String string1,java.lang.String string2,java.lang.String string3){
    try{
      return service.addPermissionToSpace(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling addPermissionToSpace.",e);}}
public boolean addPermissionsToSpace(java.lang.String[] string1,java.lang.String string2,java.lang.String string3){
    try{
      return service.addPermissionsToSpace(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling addPermissionsToSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addPersonalSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1,java.lang.String string2){
    try{
      return service.addPersonalSpace(token,remoteSpace1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addPersonalSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addPersonalSpaceWithDefaultPermissions(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1,java.lang.String string2){
    try{
      return service.addPersonalSpaceWithDefaultPermissions(token,remoteSpace1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addPersonalSpaceWithDefaultPermissions.",e);}}
public boolean addProfilePicture(java.lang.String string1,java.lang.String string2,java.lang.String string3,byte[] byte4){
    try{
      return service.addProfilePicture(token,string1,string2,string3,byte4);
    }catch(Exception e){throw new AtlassianException("Error calling addProfilePicture.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1){
    try{
      return service.addSpace(token,remoteSpace1);
    }catch(Exception e){throw new AtlassianException("Error calling addSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup addSpaceGroup(com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup remoteSpaceGroup1){
    try{
      return service.addSpaceGroup(token,remoteSpaceGroup1);
    }catch(Exception e){throw new AtlassianException("Error calling addSpaceGroup.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addSpaceWithDefaultPermissions(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1){
    try{
      return service.addSpaceWithDefaultPermissions(token,remoteSpace1);
    }catch(Exception e){throw new AtlassianException("Error calling addSpaceWithDefaultPermissions.",e);}}
public void addUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2){
    try{
      service.addUser(token,remoteUser1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addUser.",e);}}
public void addUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2,boolean boolean3){
    try{
      service.addUser(token,remoteUser1,string2,boolean3);
    }catch(Exception e){throw new AtlassianException("Error calling addUser.",e);}}
public boolean addUserToGroup(java.lang.String string1,java.lang.String string2){
    try{
      return service.addUserToGroup(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling addUserToGroup.",e);}}
public boolean changeMyPassword(java.lang.String string1,java.lang.String string2){
    try{
      return service.changeMyPassword(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling changeMyPassword.",e);}}
public boolean changeUserPassword(java.lang.String string1,java.lang.String string2){
    try{
      return service.changeUserPassword(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling changeUserPassword.",e);}}
public boolean clearIndexQueue(){
    try{
      return service.clearIndexQueue(token);
    }catch(Exception e){throw new AtlassianException("Error calling clearIndexQueue.",e);}}
public java.lang.String convertWikiToStorageFormat(java.lang.String string1){
    try{
      return service.convertWikiToStorageFormat(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling convertWikiToStorageFormat.",e);}}
public boolean deactivateUser(java.lang.String string1){
    try{
      return service.deactivateUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling deactivateUser.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteComment editComment(com.atlassian.confluence.rpc.soap.beans.RemoteComment remoteComment1){
    try{
      return service.editComment(token,remoteComment1);
    }catch(Exception e){throw new AtlassianException("Error calling editComment.",e);}}
public boolean editUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1){
    try{
      return service.editUser(token,remoteUser1);
    }catch(Exception e){throw new AtlassianException("Error calling editUser.",e);}}
public boolean emptyTrash(java.lang.String string1){
    try{
      return service.emptyTrash(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling emptyTrash.",e);}}
public java.lang.String exportSite(boolean boolean1){
    try{
      return service.exportSite(token,boolean1);
    }catch(Exception e){throw new AtlassianException("Error calling exportSite.",e);}}
public java.lang.String exportSpace(java.lang.String string1,java.lang.String string2){
    try{
      return service.exportSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling exportSpace.",e);}}
public java.lang.String exportSpace(java.lang.String string1,java.lang.String string2,boolean boolean3){
    try{
      return service.exportSpace(token,string1,string2,boolean3);
    }catch(Exception e){throw new AtlassianException("Error calling exportSpace.",e);}}
public boolean flushIndexQueue(){
    try{
      return service.flushIndexQueue(token);
    }catch(Exception e){throw new AtlassianException("Error calling flushIndexQueue.",e);}}
public java.lang.String[] getActiveUsers(boolean boolean1){
    try{
      return service.getActiveUsers(token,boolean1);
    }catch(Exception e){throw new AtlassianException("Error calling getActiveUsers.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getAncestors(long long1){
    try{
      return service.getAncestors(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getAncestors.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment getAttachment(long long1,java.lang.String string2,int int3){
    try{
      return service.getAttachment(token,long1,string2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getAttachment.",e);}}
public byte[] getAttachmentData(long long1,java.lang.String string2,int int3){
    try{
      return service.getAttachmentData(token,long1,string2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getAttachmentData.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment[] getAttachments(long long1){
    try{
      return service.getAttachments(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getAttachments.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntrySummary[] getBlogEntries(java.lang.String string1){
    try{
      return service.getBlogEntries(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getBlogEntries.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntry(long long1){
    try{
      return service.getBlogEntry(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getBlogEntry.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntryByDateAndTitle(java.lang.String string1,int int2,int int3,int int4,java.lang.String string5){
    try{
      return service.getBlogEntryByDateAndTitle(token,string1,int2,int3,int4,string5);
    }catch(Exception e){throw new AtlassianException("Error calling getBlogEntryByDateAndTitle.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntryByDayAndTitle(java.lang.String string1,int int2,java.lang.String string3){
    try{
      return service.getBlogEntryByDayAndTitle(token,string1,int2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling getBlogEntryByDayAndTitle.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getChildren(long long1){
    try{
      return service.getChildren(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getChildren.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteClusterInformation getClusterInformation(){
    try{
      return service.getClusterInformation(token);
    }catch(Exception e){throw new AtlassianException("Error calling getClusterInformation.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteNodeStatus[] getClusterNodeStatuses(){
    try{
      return service.getClusterNodeStatuses(token);
    }catch(Exception e){throw new AtlassianException("Error calling getClusterNodeStatuses.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteComment getComment(long long1){
    try{
      return service.getComment(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getComment.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteComment[] getComments(long long1){
    try{
      return service.getComments(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getComments.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteContentPermissionSet getContentPermissionSet(long long1,java.lang.String string2){
    try{
      return service.getContentPermissionSet(token,long1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getContentPermissionSet.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteContentPermissionSet[] getContentPermissionSets(long long1){
    try{
      return service.getContentPermissionSets(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getContentPermissionSets.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getDescendents(long long1){
    try{
      return service.getDescendents(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getDescendents.",e);}}
public java.lang.String[] getGroups(){
    try{
      return service.getGroups(token);
    }catch(Exception e){throw new AtlassianException("Error calling getGroups.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentById(long long1){
    try{
      return service.getLabelContentById(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getLabelContentById.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentByName(java.lang.String string1){
    try{
      return service.getLabelContentByName(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getLabelContentByName.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1){
    try{
      return service.getLabelContentByObject(token,remoteLabel1);
    }catch(Exception e){throw new AtlassianException("Error calling getLabelContentByObject.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getLabelsByDetail(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4){
    try{
      return service.getLabelsByDetail(token,string1,string2,string3,string4);
    }catch(Exception e){throw new AtlassianException("Error calling getLabelsByDetail.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getLabelsById(long long1){
    try{
      return service.getLabelsById(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getLabelsById.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getMostPopularLabels(int int1){
    try{
      return service.getMostPopularLabels(token,int1);
    }catch(Exception e){throw new AtlassianException("Error calling getMostPopularLabels.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getMostPopularLabelsInSpace(java.lang.String string1,int int2){
    try{
      return service.getMostPopularLabelsInSpace(token,string1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling getMostPopularLabelsInSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePage getPage(long long1){
    try{
      return service.getPage(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getPage.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePage getPage(java.lang.String string1,java.lang.String string2){
    try{
      return service.getPage(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getPage.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageHistory[] getPageHistory(long long1){
    try{
      return service.getPageHistory(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getPageHistory.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePermission[] getPagePermissions(long long1){
    try{
      return service.getPagePermissions(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getPagePermissions.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary getPageSummary(long long1){
    try{
      return service.getPageSummary(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getPageSummary.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary getPageSummary(java.lang.String string1,java.lang.String string2){
    try{
      return service.getPageSummary(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getPageSummary.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getPages(java.lang.String string1){
    try{
      return service.getPages(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getPages.",e);}}
public java.lang.String[] getPermissions(java.lang.String string1){
    try{
      return service.getPermissions(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getPermissions.",e);}}
public java.lang.String[] getPermissionsForUser(java.lang.String string1,java.lang.String string2){
    try{
      return service.getPermissionsForUser(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getPermissionsForUser.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRecentlyUsedLabels(int int1){
    try{
      return service.getRecentlyUsedLabels(token,int1);
    }catch(Exception e){throw new AtlassianException("Error calling getRecentlyUsedLabels.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRecentlyUsedLabelsInSpace(java.lang.String string1,int int2){
    try{
      return service.getRecentlyUsedLabelsInSpace(token,string1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling getRecentlyUsedLabelsInSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRelatedLabels(java.lang.String string1,int int2){
    try{
      return service.getRelatedLabels(token,string1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling getRelatedLabels.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRelatedLabelsInSpace(java.lang.String string1,java.lang.String string2,int int3){
    try{
      return service.getRelatedLabelsInSpace(token,string1,string2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getRelatedLabelsInSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteServerInfo getServerInfo(){
    try{
      return service.getServerInfo(token);
    }catch(Exception e){throw new AtlassianException("Error calling getServerInfo.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace getSpace(java.lang.String string1){
    try{
      return service.getSpace(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup getSpaceGroup(java.lang.String string1){
    try{
      return service.getSpaceGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpaceGroup.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup[] getSpaceGroups(){
    try{
      return service.getSpaceGroups(token);
    }catch(Exception e){throw new AtlassianException("Error calling getSpaceGroups.",e);}}
public java.lang.String[] getSpaceLevelPermissions(){
    try{
      return service.getSpaceLevelPermissions(token);
    }catch(Exception e){throw new AtlassianException("Error calling getSpaceLevelPermissions.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpacePermissionSet getSpacePermissionSet(java.lang.String string1,java.lang.String string2){
    try{
      return service.getSpacePermissionSet(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getSpacePermissionSet.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpacePermissionSet[] getSpacePermissionSets(java.lang.String string1){
    try{
      return service.getSpacePermissionSets(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpacePermissionSets.",e);}}
public java.lang.String getSpaceStatus(java.lang.String string1){
    try{
      return service.getSpaceStatus(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpaceStatus.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary[] getSpaces(){
    try{
      return service.getSpaces(token);
    }catch(Exception e){throw new AtlassianException("Error calling getSpaces.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace[] getSpacesContainingContentWithLabel(java.lang.String string1){
    try{
      return service.getSpacesContainingContentWithLabel(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpacesContainingContentWithLabel.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary[] getSpacesInGroup(java.lang.String string1){
    try{
      return service.getSpacesInGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpacesInGroup.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace[] getSpacesWithLabel(java.lang.String string1){
    try{
      return service.getSpacesWithLabel(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getSpacesWithLabel.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getTopLevelPages(java.lang.String string1){
    try{
      return service.getTopLevelPages(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getTopLevelPages.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteContentSummaries getTrashContents(java.lang.String string1,int int2,int int3){
    try{
      return service.getTrashContents(token,string1,int2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling getTrashContents.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteUser getUser(java.lang.String string1){
    try{
      return service.getUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getUser.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteConfluenceUser getUserByKey(java.lang.String string1){
    try{
      return service.getUserByKey(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getUserByKey.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteConfluenceUser getUserByName(java.lang.String string1){
    try{
      return service.getUserByName(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getUserByName.",e);}}
public java.lang.String[] getUserGroups(java.lang.String string1){
    try{
      return service.getUserGroups(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getUserGroups.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteUserInformation getUserInformation(java.lang.String string1){
    try{
      return service.getUserInformation(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getUserInformation.",e);}}
public boolean getUserPreferenceBoolean(java.lang.String string1,java.lang.String string2){
    try{
      return service.getUserPreferenceBoolean(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getUserPreferenceBoolean.",e);}}
public long getUserPreferenceLong(java.lang.String string1,java.lang.String string2){
    try{
      return service.getUserPreferenceLong(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getUserPreferenceLong.",e);}}
public java.lang.String getUserPreferenceString(java.lang.String string1,java.lang.String string2){
    try{
      return service.getUserPreferenceString(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling getUserPreferenceString.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteUser[] getWatchersForPage(long long1){
    try{
      return service.getWatchersForPage(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling getWatchersForPage.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteUser[] getWatchersForSpace(java.lang.String string1){
    try{
      return service.getWatchersForSpace(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling getWatchersForSpace.",e);}}
public boolean hasGroup(java.lang.String string1){
    try{
      return service.hasGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling hasGroup.",e);}}
public boolean hasUser(java.lang.String string1){
    try{
      return service.hasUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling hasUser.",e);}}
public boolean importSpace(byte[] byte1){
    try{
      return service.importSpace(token,byte1);
    }catch(Exception e){throw new AtlassianException("Error calling importSpace.",e);}}
public boolean installPlugin(java.lang.String string1,byte[] byte2){
    try{
      return service.installPlugin(token,string1,byte2);
    }catch(Exception e){throw new AtlassianException("Error calling installPlugin.",e);}}
public boolean isActiveUser(java.lang.String string1){
    try{
      return service.isActiveUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling isActiveUser.",e);}}
public boolean isDarkFeatureEnabled(java.lang.String string1){
    try{
      return service.isDarkFeatureEnabled(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling isDarkFeatureEnabled.",e);}}
public boolean isPluginEnabled(java.lang.String string1){
    try{
      return service.isPluginEnabled(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling isPluginEnabled.",e);}}
public boolean isPluginInstalled(java.lang.String string1){
    try{
      return service.isPluginInstalled(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling isPluginInstalled.",e);}}
public boolean isWatchingPage(long long1,java.lang.String string2){
    try{
      return service.isWatchingPage(token,long1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling isWatchingPage.",e);}}
public boolean isWatchingSpace(java.lang.String string1,java.lang.String string2){
    try{
      return service.isWatchingSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling isWatchingSpace.",e);}}
public boolean isWatchingSpaceForType(java.lang.String string1,java.lang.String string2,java.lang.String string3){
    try{
      return service.isWatchingSpaceForType(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling isWatchingSpaceForType.",e);}}
public java.lang.String login(java.lang.String string1){
    try{
      return service.login(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling login.",e);}}
public boolean logout(){
    try{
      return service.logout(token);
    }catch(Exception e){throw new AtlassianException("Error calling logout.",e);}}
public boolean moveAttachment(long long1,java.lang.String string2,long long3,java.lang.String string4){
    try{
      return service.moveAttachment(token,long1,string2,long3,string4);
    }catch(Exception e){throw new AtlassianException("Error calling moveAttachment.",e);}}
public boolean movePage(long long1,long long2,java.lang.String string3){
    try{
      return service.movePage(token,long1,long2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling movePage.",e);}}
public boolean movePageToTopLevel(long long1,java.lang.String string2){
    try{
      return service.movePageToTopLevel(token,long1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling movePageToTopLevel.",e);}}
public java.lang.String performBackup(boolean boolean1){
    try{
      return service.performBackup(token,boolean1);
    }catch(Exception e){throw new AtlassianException("Error calling performBackup.",e);}}
public boolean purgeFromTrash(java.lang.String string1,long long2){
    try{
      return service.purgeFromTrash(token,string1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling purgeFromTrash.",e);}}
public boolean reactivateUser(java.lang.String string1){
    try{
      return service.reactivateUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling reactivateUser.",e);}}
public boolean removeAllPermissionsForGroup(java.lang.String string1){
    try{
      return service.removeAllPermissionsForGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling removeAllPermissionsForGroup.",e);}}
public boolean removeAnonymousPermissionFromSpace(java.lang.String string1,java.lang.String string2){
    try{
      return service.removeAnonymousPermissionFromSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeAnonymousPermissionFromSpace.",e);}}
public boolean removeAnonymousUsePermission(){
    try{
      return service.removeAnonymousUsePermission(token);
    }catch(Exception e){throw new AtlassianException("Error calling removeAnonymousUsePermission.",e);}}
public boolean removeAnonymousViewUserProfilePermission(){
    try{
      return service.removeAnonymousViewUserProfilePermission(token);
    }catch(Exception e){throw new AtlassianException("Error calling removeAnonymousViewUserProfilePermission.",e);}}
public boolean removeAttachment(long long1,java.lang.String string2){
    try{
      return service.removeAttachment(token,long1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeAttachment.",e);}}
public boolean removeComment(long long1){
    try{
      return service.removeComment(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling removeComment.",e);}}
public boolean removeGlobalPermission(java.lang.String string1,java.lang.String string2){
    try{
      return service.removeGlobalPermission(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeGlobalPermission.",e);}}
public boolean removeGroup(java.lang.String string1,java.lang.String string2){
    try{
      return service.removeGroup(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeGroup.",e);}}
public boolean removeLabelById(long long1,long long2){
    try{
      return service.removeLabelById(token,long1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling removeLabelById.",e);}}
public boolean removeLabelByName(java.lang.String string1,long long2){
    try{
      return service.removeLabelByName(token,string1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling removeLabelByName.",e);}}
public boolean removeLabelByNameFromSpace(java.lang.String string1,java.lang.String string2){
    try{
      return service.removeLabelByNameFromSpace(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeLabelByNameFromSpace.",e);}}
public boolean removeLabelByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1,long long2){
    try{
      return service.removeLabelByObject(token,remoteLabel1,long2);
    }catch(Exception e){throw new AtlassianException("Error calling removeLabelByObject.",e);}}
public boolean removePage(long long1){
    try{
      return service.removePage(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling removePage.",e);}}
public boolean removePageVersionById(long long1){
    try{
      return service.removePageVersionById(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling removePageVersionById.",e);}}
public boolean removePageVersionByVersion(long long1,int int2){
    try{
      return service.removePageVersionByVersion(token,long1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling removePageVersionByVersion.",e);}}
public boolean removePageWatch(long long1){
    try{
      return service.removePageWatch(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling removePageWatch.",e);}}
public boolean removePageWatchForUser(long long1,java.lang.String string2){
    try{
      return service.removePageWatchForUser(token,long1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removePageWatchForUser.",e);}}
public boolean removePermissionFromSpace(java.lang.String string1,java.lang.String string2,java.lang.String string3){
    try{
      return service.removePermissionFromSpace(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling removePermissionFromSpace.",e);}}
public boolean removeSpace(java.lang.String string1){
    try{
      return service.removeSpace(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling removeSpace.",e);}}
public boolean removeSpaceGroup(java.lang.String string1){
    try{
      return service.removeSpaceGroup(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling removeSpaceGroup.",e);}}
public boolean removeSpaceWatch(java.lang.String string1){
    try{
      return service.removeSpaceWatch(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling removeSpaceWatch.",e);}}
public boolean removeUser(java.lang.String string1){
    try{
      return service.removeUser(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling removeUser.",e);}}
public boolean removeUserFromGroup(java.lang.String string1,java.lang.String string2){
    try{
      return service.removeUserFromGroup(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling removeUserFromGroup.",e);}}
public boolean renameUser(java.lang.String string1,java.lang.String string2){
    try{
      return service.renameUser(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling renameUser.",e);}}
public java.lang.String[] renameUsers(java.util.HashMap hashMap1){
    try{
      return service.renameUsers(token,hashMap1);
    }catch(Exception e){throw new AtlassianException("Error calling renameUsers.",e);}}
public java.lang.String renderContent(java.lang.String string1,long long2,java.lang.String string3){
    try{
      return service.renderContent(token,string1,long2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling renderContent.",e);}}
public java.lang.String renderContent(java.lang.String string1,long long2,java.lang.String string3,java.util.HashMap hashMap4){
    try{
      return service.renderContent(token,string1,long2,string3,hashMap4);
    }catch(Exception e){throw new AtlassianException("Error calling renderContent.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] search(java.lang.String string1,int int2){
    try{
      return service.search(token,string1,int2);
    }catch(Exception e){throw new AtlassianException("Error calling search.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] search(java.lang.String string1,java.util.HashMap hashMap2,int int3){
    try{
      return service.search(token,string1,hashMap2,int3);
    }catch(Exception e){throw new AtlassianException("Error calling search.",e);}}
public boolean setContentPermissions(long long1,java.lang.String string2,com.atlassian.confluence.rpc.soap.beans.RemoteContentPermission[] remoteContentPermission3){
    try{
      return service.setContentPermissions(token,long1,string2,remoteContentPermission3);
    }catch(Exception e){throw new AtlassianException("Error calling setContentPermissions.",e);}}
public boolean setEnableAnonymousAccess(boolean boolean1){
    try{
      return service.setEnableAnonymousAccess(token,boolean1);
    }catch(Exception e){throw new AtlassianException("Error calling setEnableAnonymousAccess.",e);}}
public boolean setEnableWysiwyg(boolean boolean1){
    try{
      return service.setEnableWysiwyg(token,boolean1);
    }catch(Exception e){throw new AtlassianException("Error calling setEnableWysiwyg.",e);}}
public boolean setSpaceStatus(java.lang.String string1,java.lang.String string2){
    try{
      return service.setSpaceStatus(token,string1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling setSpaceStatus.",e);}}
public boolean setUserInformation(com.atlassian.confluence.rpc.soap.beans.RemoteUserInformation remoteUserInformation1){
    try{
      return service.setUserInformation(token,remoteUserInformation1);
    }catch(Exception e){throw new AtlassianException("Error calling setUserInformation.",e);}}
public boolean setUserPreferenceBoolean(java.lang.String string1,java.lang.String string2,boolean boolean3){
    try{
      return service.setUserPreferenceBoolean(token,string1,string2,boolean3);
    }catch(Exception e){throw new AtlassianException("Error calling setUserPreferenceBoolean.",e);}}
public boolean setUserPreferenceLong(java.lang.String string1,java.lang.String string2,long long3){
    try{
      return service.setUserPreferenceLong(token,string1,string2,long3);
    }catch(Exception e){throw new AtlassianException("Error calling setUserPreferenceLong.",e);}}
public boolean setUserPreferenceString(java.lang.String string1,java.lang.String string2,java.lang.String string3){
    try{
      return service.setUserPreferenceString(token,string1,string2,string3);
    }catch(Exception e){throw new AtlassianException("Error calling setUserPreferenceString.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry storeBlogEntry(com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry remoteBlogEntry1){
    try{
      return service.storeBlogEntry(token,remoteBlogEntry1);
    }catch(Exception e){throw new AtlassianException("Error calling storeBlogEntry.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePage storePage(com.atlassian.confluence.rpc.soap.beans.RemotePage remotePage1){
    try{
      return service.storePage(token,remotePage1);
    }catch(Exception e){throw new AtlassianException("Error calling storePage.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemoteSpace storeSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1){
    try{
      return service.storeSpace(token,remoteSpace1);
    }catch(Exception e){throw new AtlassianException("Error calling storeSpace.",e);}}
public com.atlassian.confluence.rpc.soap.beans.RemotePage updatePage(com.atlassian.confluence.rpc.soap.beans.RemotePage remotePage1,com.atlassian.confluence.rpc.soap.beans.RemotePageUpdateOptions remotePageUpdateOptions2){
    try{
      return service.updatePage(token,remotePage1,remotePageUpdateOptions2);
    }catch(Exception e){throw new AtlassianException("Error calling updatePage.",e);}}
public boolean watchPage(long long1){
    try{
      return service.watchPage(token,long1);
    }catch(Exception e){throw new AtlassianException("Error calling watchPage.",e);}}
public boolean watchPageForUser(long long1,java.lang.String string2){
    try{
      return service.watchPageForUser(token,long1,string2);
    }catch(Exception e){throw new AtlassianException("Error calling watchPageForUser.",e);}}
public boolean watchSpace(java.lang.String string1){
    try{
      return service.watchSpace(token,string1);
    }catch(Exception e){throw new AtlassianException("Error calling watchSpace.",e);}}
}
