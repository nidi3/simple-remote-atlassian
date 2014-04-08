package guru.nidi.atlassian.remote.confluence;
public interface ConfluenceService{
  String getBaseUrl();
  public com.atlassian.confluence.rpc.soap.beans.RemotePage getOrCreatePage(long long0,java.lang.String string1);
  public guru.nidi.atlassian.remote.confluence.ConfluenceService getService();
  public boolean addAnonymousPermissionToSpace(java.lang.String string1,java.lang.String string2);
  public boolean addAnonymousPermissionsToSpace(java.lang.String[] string1,java.lang.String string2);
  public boolean addAnonymousUsePermission();
  public boolean addAnonymousViewUserProfilePermission();
  public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment addAttachment(long long1,com.atlassian.confluence.rpc.soap.beans.RemoteAttachment remoteAttachment2,byte[] byte3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment addAttachment(com.atlassian.confluence.rpc.soap.beans.RemoteAttachment remoteAttachment1,byte[] byte2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteComment addComment(com.atlassian.confluence.rpc.soap.beans.RemoteComment remoteComment1);
  public boolean addGlobalPermission(java.lang.String string1,java.lang.String string2);
  public boolean addGlobalPermissions(java.lang.String[] string1,java.lang.String string2);
  public boolean addGroup(java.lang.String string1);
  public boolean addLabelById(long long1,long long2);
  public boolean addLabelByName(java.lang.String string1,long long2);
  public boolean addLabelByNameToSpace(java.lang.String string1,java.lang.String string2);
  public boolean addLabelByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1,long long2);
  public boolean addPermissionToSpace(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  public boolean addPermissionsToSpace(java.lang.String[] string1,java.lang.String string2,java.lang.String string3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addPersonalSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1,java.lang.String string2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addPersonalSpaceWithDefaultPermissions(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1,java.lang.String string2);
  public boolean addProfilePicture(java.lang.String string1,java.lang.String string2,java.lang.String string3,byte[] byte4);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup addSpaceGroup(com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup remoteSpaceGroup1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace addSpaceWithDefaultPermissions(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1);
  public void addUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2,boolean boolean3);
  public void addUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1,java.lang.String string2);
  public boolean addUserToGroup(java.lang.String string1,java.lang.String string2);
  public boolean changeMyPassword(java.lang.String string1,java.lang.String string2);
  public boolean changeUserPassword(java.lang.String string1,java.lang.String string2);
  public boolean clearIndexQueue();
  public java.lang.String convertWikiToStorageFormat(java.lang.String string1);
  public boolean deactivateUser(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteComment editComment(com.atlassian.confluence.rpc.soap.beans.RemoteComment remoteComment1);
  public boolean editUser(com.atlassian.confluence.rpc.soap.beans.RemoteUser remoteUser1);
  public boolean emptyTrash(java.lang.String string1);
  public java.lang.String exportSite(boolean boolean1);
  public java.lang.String exportSpace(java.lang.String string1,java.lang.String string2);
  public java.lang.String exportSpace(java.lang.String string1,java.lang.String string2,boolean boolean3);
  public boolean flushIndexQueue();
  public java.lang.String[] getActiveUsers(boolean boolean1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getAncestors(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment getAttachment(long long1,java.lang.String string2,int int3);
  public byte[] getAttachmentData(long long1,java.lang.String string2,int int3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteAttachment[] getAttachments(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntrySummary[] getBlogEntries(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntry(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntryByDateAndTitle(java.lang.String string1,int int2,int int3,int int4,java.lang.String string5);
  public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry getBlogEntryByDayAndTitle(java.lang.String string1,int int2,java.lang.String string3);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getChildren(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteClusterInformation getClusterInformation();
  public com.atlassian.confluence.rpc.soap.beans.RemoteNodeStatus[] getClusterNodeStatuses();
  public com.atlassian.confluence.rpc.soap.beans.RemoteComment getComment(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteComment[] getComments(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteContentPermissionSet getContentPermissionSet(long long1,java.lang.String string2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteContentPermissionSet[] getContentPermissionSets(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getDescendents(long long1);
  public java.lang.String[] getGroups();
  public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentById(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentByName(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] getLabelContentByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getLabelsByDetail(java.lang.String string1,java.lang.String string2,java.lang.String string3,java.lang.String string4);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getLabelsById(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getMostPopularLabels(int int1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getMostPopularLabelsInSpace(java.lang.String string1,int int2);
  public com.atlassian.confluence.rpc.soap.beans.RemotePage getPage(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePage getPage(java.lang.String string1,java.lang.String string2);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageHistory[] getPageHistory(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePermission[] getPagePermissions(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary getPageSummary(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary getPageSummary(java.lang.String string1,java.lang.String string2);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getPages(java.lang.String string1);
  public java.lang.String[] getPermissions(java.lang.String string1);
  public java.lang.String[] getPermissionsForUser(java.lang.String string1,java.lang.String string2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRecentlyUsedLabels(int int1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRecentlyUsedLabelsInSpace(java.lang.String string1,int int2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRelatedLabels(java.lang.String string1,int int2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteLabel[] getRelatedLabelsInSpace(java.lang.String string1,java.lang.String string2,int int3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteServerInfo getServerInfo();
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace getSpace(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup getSpaceGroup(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceGroup[] getSpaceGroups();
  public java.lang.String[] getSpaceLevelPermissions();
  public java.lang.String getSpaceStatus(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary[] getSpaces();
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace[] getSpacesContainingContentWithLabel(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpaceSummary[] getSpacesInGroup(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace[] getSpacesWithLabel(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePageSummary[] getTopLevelPages(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteContentSummaries getTrashContents(java.lang.String string1,int int2,int int3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteUser getUser(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteConfluenceUser getUserByKey(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteConfluenceUser getUserByName(java.lang.String string1);
  public java.lang.String[] getUserGroups(java.lang.String string1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteUserInformation getUserInformation(java.lang.String string1);
  public boolean getUserPreferenceBoolean(java.lang.String string1,java.lang.String string2);
  public long getUserPreferenceLong(java.lang.String string1,java.lang.String string2);
  public java.lang.String getUserPreferenceString(java.lang.String string1,java.lang.String string2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteUser[] getWatchersForPage(long long1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteUser[] getWatchersForSpace(java.lang.String string1);
  public boolean hasGroup(java.lang.String string1);
  public boolean hasUser(java.lang.String string1);
  public boolean importSpace(byte[] byte1);
  public boolean installPlugin(java.lang.String string1,byte[] byte2);
  public boolean isActiveUser(java.lang.String string1);
  public boolean isDarkFeatureEnabled(java.lang.String string1);
  public boolean isPluginEnabled(java.lang.String string1);
  public boolean isPluginInstalled(java.lang.String string1);
  public boolean isWatchingPage(long long1,java.lang.String string2);
  public boolean isWatchingSpace(java.lang.String string1,java.lang.String string2);
  public boolean isWatchingSpaceForType(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  public java.lang.String login(java.lang.String string1);
  public boolean logout();
  public boolean moveAttachment(long long1,java.lang.String string2,long long3,java.lang.String string4);
  public boolean movePage(long long1,long long2,java.lang.String string3);
  public boolean movePageToTopLevel(long long1,java.lang.String string2);
  public java.lang.String performBackup(boolean boolean1);
  public boolean purgeFromTrash(java.lang.String string1,long long2);
  public boolean reactivateUser(java.lang.String string1);
  public boolean removeAllPermissionsForGroup(java.lang.String string1);
  public boolean removeAnonymousPermissionFromSpace(java.lang.String string1,java.lang.String string2);
  public boolean removeAnonymousUsePermission();
  public boolean removeAnonymousViewUserProfilePermission();
  public boolean removeAttachment(long long1,java.lang.String string2);
  public boolean removeComment(long long1);
  public boolean removeGlobalPermission(java.lang.String string1,java.lang.String string2);
  public boolean removeGroup(java.lang.String string1,java.lang.String string2);
  public boolean removeLabelById(long long1,long long2);
  public boolean removeLabelByName(java.lang.String string1,long long2);
  public boolean removeLabelByNameFromSpace(java.lang.String string1,java.lang.String string2);
  public boolean removeLabelByObject(com.atlassian.confluence.rpc.soap.beans.RemoteLabel remoteLabel1,long long2);
  public boolean removePage(long long1);
  public boolean removePageVersionById(long long1);
  public boolean removePageVersionByVersion(long long1,int int2);
  public boolean removePageWatch(long long1);
  public boolean removePageWatchForUser(long long1,java.lang.String string2);
  public boolean removePermissionFromSpace(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  public boolean removeSpace(java.lang.String string1);
  public boolean removeSpaceGroup(java.lang.String string1);
  public boolean removeSpaceWatch(java.lang.String string1);
  public boolean removeUser(java.lang.String string1);
  public boolean removeUserFromGroup(java.lang.String string1,java.lang.String string2);
  public boolean renameUser(java.lang.String string1,java.lang.String string2);
  public java.lang.String[] renameUsers(java.util.HashMap hashMap1);
  public java.lang.String renderContent(java.lang.String string1,long long2,java.lang.String string3,java.util.HashMap hashMap4);
  public java.lang.String renderContent(java.lang.String string1,long long2,java.lang.String string3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] search(java.lang.String string1,int int2);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSearchResult[] search(java.lang.String string1,java.util.HashMap hashMap2,int int3);
  public boolean setContentPermissions(long long1,java.lang.String string2,com.atlassian.confluence.rpc.soap.beans.RemoteContentPermission[] remoteContentPermission3);
  public boolean setEnableAnonymousAccess(boolean boolean1);
  public boolean setEnableWysiwyg(boolean boolean1);
  public boolean setSpaceStatus(java.lang.String string1,java.lang.String string2);
  public boolean setUserInformation(com.atlassian.confluence.rpc.soap.beans.RemoteUserInformation remoteUserInformation1);
  public boolean setUserPreferenceBoolean(java.lang.String string1,java.lang.String string2,boolean boolean3);
  public boolean setUserPreferenceLong(java.lang.String string1,java.lang.String string2,long long3);
  public boolean setUserPreferenceString(java.lang.String string1,java.lang.String string2,java.lang.String string3);
  public com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry storeBlogEntry(com.atlassian.confluence.rpc.soap.beans.RemoteBlogEntry remoteBlogEntry1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePage storePage(com.atlassian.confluence.rpc.soap.beans.RemotePage remotePage1);
  public com.atlassian.confluence.rpc.soap.beans.RemoteSpace storeSpace(com.atlassian.confluence.rpc.soap.beans.RemoteSpace remoteSpace1);
  public com.atlassian.confluence.rpc.soap.beans.RemotePage updatePage(com.atlassian.confluence.rpc.soap.beans.RemotePage remotePage1,com.atlassian.confluence.rpc.soap.beans.RemotePageUpdateOptions remotePageUpdateOptions2);
  public boolean watchPage(long long1);
  public boolean watchPageForUser(long long1,java.lang.String string2);
  public boolean watchSpace(java.lang.String string1);
}
