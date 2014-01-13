package stni.atlassian.remote.confluence;
public interface ConfluenceService{
  String getBaseUrl();
  boolean addAnonymousPermissionToSpace(java.lang.String string1,java.lang.String string2);
  boolean addAnonymousPermissionsToSpace(java.lang.String[] string1,java.lang.String string2);
  boolean addAnonymousUsePermission();
  boolean addAnonymousViewUserProfilePermission();
  com.atlassian.confluence.rpc.soap.beans.RemoteAttachment addAttachment(long long1,com.atlassian.confluence.rpc.soap.beans.RemoteAttachment remoteAttachment2,byte[] byte3);
  com.atlassian.confluence.rpc.soap.beans.RemoteAttachment addAttachment(com.atlassian.confluence.rpc.soap.beans.RemoteAttachment remoteAttachment1,byte[] byte2);
  com.atlassian.confluence.rpc.soap.beans.RemoteComment addComment(com.atlassian.confluence.rpc.soap.beans.RemoteComment remoteComment1);
  boolean addGlobalPermission(java.lang.String string1,java.lang.String string2);
  boolean addGlobalPermissions(java.lang.String[] string1,java.lang.String string2);
  boolean addGroup(java.lang.String string1);
  boolean addLabelById(long long1,long long2);
  boolean addLabelByName(java.lang.String string1,long long2);
  boolean addLabelByNameToSpace(java.lang.String string1,java.lang.String string2);
  boolean addLabelByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1,long long2);
  boolean addPermissionToSpace(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  boolean addPermissionsToSpace(java.lang.String[] string1,java.lang.String string2,java.lang.String string3);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace addPersonalSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1,java.lang.String string2);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace addPersonalSpaceWithDefaultPermissions(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1,java.lang.String string2);
  boolean addProfilePicture(java.lang.String string1,java.lang.String string2,java.lang.String string3,byte[] byte4);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace addSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup addSpaceGroup(com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup remoteSpaceGroup1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace addSpaceWithDefaultPermissions(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1);
  void addUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2,boolean boolean3);
  void addUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2);
  boolean addUserToGroup(java.lang.String string1,java.lang.String string2);
  boolean changeMyPassword(java.lang.String string1,java.lang.String string2);
  boolean changeUserPassword(java.lang.String string1,java.lang.String string2);
  boolean clearIndexQueue();
  java.lang.String convertWikiToStorageFormat(java.lang.String string1);
  boolean deactivateUser(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteComment editComment(com.atlassian.confluence.rpc.soap.beans.RemoteComment remoteComment1);
  boolean editUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1);
  boolean emptyTrash(java.lang.String string1);
  java.lang.String exportSite(boolean boolean1);
  java.lang.String exportSpace(java.lang.String string1,java.lang.String string2,boolean boolean3);
  java.lang.String exportSpace(java.lang.String string1,java.lang.String string2);
  boolean flushIndexQueue();
  java.lang.String[] getActiveUsers(boolean boolean1);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getAncestors(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteAttachment getAttachment(long long1,java.lang.String string2,int int3);
  byte[] getAttachmentData(long long1,java.lang.String string2,int int3);
  com.atlassian.confluence.rpc.soap.beans.RemoteAttachment[] getAttachments(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntrySummary[] getBlogEntries(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntry(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntryByDateAndTitle(java.lang.String string1,int int2,int int3,int int4,java.lang.String string5);
  com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntryByDayAndTitle(java.lang.String string1,int int2,java.lang.String string3);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getChildren(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteClusterInformation getClusterInformation();
  com.atlassian.confluence.rpc.soap.beans.RemoteNodeStatus[] getClusterNodeStatuses();
  com.atlassian.confluence.rpc.soap.beans.RemoteComment getComment(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteComment[] getComments(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteContentPermissionSet getContentPermissionSet(long long1,java.lang.String string2);
  com.atlassian.confluence.rpc.soap.beans.RemoteContentPermissionSet[] getContentPermissionSets(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getDescendents(long long1);
  java.lang.String[] getGroups();
  com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentById(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentByName(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getLabelsByDetail(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getLabelsById(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getMostPopularLabels(int int1);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getMostPopularLabelsInSpace(java.lang.String string1,int int2);
  com.atlassian.confluence.rpc.soap.beans.RemotePage getPage(java.lang.String string1,java.lang.String string2);
  com.atlassian.confluence.rpc.soap.beans.RemotePage getPage(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemotePageHistory[] getPageHistory(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemotePermission[] getPagePermissions(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary getPageSummary(java.lang.String string1,java.lang.String string2);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary getPageSummary(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getPages(java.lang.String string1);
  java.lang.String[] getPermissions(java.lang.String string1);
  java.lang.String[] getPermissionsForUser(java.lang.String string1,java.lang.String string2);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRecentlyUsedLabels(int int1);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRecentlyUsedLabelsInSpace(java.lang.String string1,int int2);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRelatedLabels(java.lang.String string1,int int2);
  com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRelatedLabelsInSpace(java.lang.String string1,java.lang.String string2,int int3);
  com.atlassian.confluence.rpc.soap.beans.RemoteServerInfo getServerInfo();
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace getSpace(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup getSpaceGroup(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup[] getSpaceGroups();
  java.lang.String[] getSpaceLevelPermissions();
  java.lang.String getSpaceStatus(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary[] getSpaces();
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace[] getSpacesContainingContentWithLabel(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary[] getSpacesInGroup(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace[] getSpacesWithLabel(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getTopLevelPages(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteContentSummaries getTrashContents(java.lang.String string1,int int2,int int3);
  com.atlassian.confluence.rpc.soap.beans.RemoteUser getUser(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteConfluenceUser getUserByKey(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteConfluenceUser getUserByName(java.lang.String string1);
  java.lang.String[] getUserGroups(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemoteUserInformation getUserInformation(java.lang.String string1);
  boolean getUserPreferenceBoolean(java.lang.String string1,java.lang.String string2);
  long getUserPreferenceLong(java.lang.String string1,java.lang.String string2);
  java.lang.String getUserPreferenceString(java.lang.String string1,java.lang.String string2);
  com.atlassian.confluence.rpc.soap.beans.RemoteUser[] getWatchersForPage(long long1);
  com.atlassian.confluence.rpc.soap.beans.RemoteUser[] getWatchersForSpace(java.lang.String string1);
  boolean hasGroup(java.lang.String string1);
  boolean hasUser(java.lang.String string1);
  boolean importSpace(byte[] byte1);
  boolean installPlugin(java.lang.String string1,byte[] byte2);
  boolean isActiveUser(java.lang.String string1);
  boolean isDarkFeatureEnabled(java.lang.String string1);
  boolean isPluginEnabled(java.lang.String string1);
  boolean isPluginInstalled(java.lang.String string1);
  boolean isWatchingPage(long long1,java.lang.String string2);
  boolean isWatchingSpace(java.lang.String string1,java.lang.String string2);
  boolean isWatchingSpaceForType(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  java.lang.String login(java.lang.String string1);
  boolean logout();
  boolean moveAttachment(long long1,java.lang.String string2,long long3,java.lang.String string4);
  boolean movePage(long long1,long long2,java.lang.String string3);
  boolean movePageToTopLevel(long long1,java.lang.String string2);
  java.lang.String performBackup(boolean boolean1);
  boolean purgeFromTrash(java.lang.String string1,long long2);
  boolean reactivateUser(java.lang.String string1);
  boolean removeAllPermissionsForGroup(java.lang.String string1);
  boolean removeAnonymousPermissionFromSpace(java.lang.String string1,java.lang.String string2);
  boolean removeAnonymousUsePermission();
  boolean removeAnonymousViewUserProfilePermission();
  boolean removeAttachment(long long1,java.lang.String string2);
  boolean removeComment(long long1);
  boolean removeGlobalPermission(java.lang.String string1,java.lang.String string2);
  boolean removeGroup(java.lang.String string1,java.lang.String string2);
  boolean removeLabelById(long long1,long long2);
  boolean removeLabelByName(java.lang.String string1,long long2);
  boolean removeLabelByNameFromSpace(java.lang.String string1,java.lang.String string2);
  boolean removeLabelByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1,long long2);
  boolean removePage(long long1);
  boolean removePageVersionById(long long1);
  boolean removePageVersionByVersion(long long1,int int2);
  boolean removePageWatch(long long1);
  boolean removePageWatchForUser(long long1,java.lang.String string2);
  boolean removePermissionFromSpace(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  boolean removeSpace(java.lang.String string1);
  boolean removeSpaceGroup(java.lang.String string1);
  boolean removeSpaceWatch(java.lang.String string1);
  boolean removeUser(java.lang.String string1);
  boolean removeUserFromGroup(java.lang.String string1,java.lang.String string2);
  boolean renameUser(java.lang.String string1,java.lang.String string2);
  java.lang.String[] renameUsers(java.util.HashMap hashMap1);
  java.lang.String renderContent(java.lang.String string1,long long2,java.lang.String string3);
  java.lang.String renderContent(java.lang.String string1,long long2,java.lang.String string3,java.util.HashMap hashMap4);
  com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] search(java.lang.String string1,java.util.HashMap hashMap2,int int3);
  com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] search(java.lang.String string1,int int2);
  boolean setContentPermissions(long long1,java.lang.String string2,com.atlassian.confluence.rpc.soap.beans.RemoteContentPermission[] remoteContentPermission3);
  boolean setEnableAnonymousAccess(boolean boolean1);
  boolean setEnableWysiwyg(boolean boolean1);
  boolean setSpaceStatus(java.lang.String string1,java.lang.String string2);
  boolean setUserInformation(com.atlassian.confluence.rpc.soap.beans.RemoteUserInformation remoteUserInformation1);
  boolean setUserPreferenceBoolean(java.lang.String string1,java.lang.String string2,boolean boolean3);
  boolean setUserPreferenceLong(java.lang.String string1,java.lang.String string2,long long3);
  boolean setUserPreferenceString(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry storeBlogEntry(com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry remoteBlogEntry1);
  com.atlassian.confluence.rpc.soap.beans.RemotePage storePage(com.atlassian.confluence.rpc.soap.beans.RemotePage remotePage1);
  com.atlassian.confluence.rpc.soap.beans.RemoteSpace storeSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1);
  com.atlassian.confluence.rpc.soap.beans.RemotePage updatePage(com.atlassian.confluence.rpc.soap.beans.RemotePage remotePage1,com.atlassian.confluence.rpc.soap.beans.RemotePageUpdateOptions remotePageUpdateOptions2);
  boolean watchPage(long long1);
  boolean watchPageForUser(long long1,java.lang.String string2);
  boolean watchSpace(java.lang.String string1);
  com.atlassian.confluence.rpc.soap.beans.RemotePage getOrCreatePage(long long0,java.lang.String string1);
  stni.atlassian.remote.confluence.ConfluenceService getService();
}
