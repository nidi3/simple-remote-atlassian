package stni.atlassian.remote;

import com.atlassian.confluence.rpc.soap.beans.RemotePage;

/**
 *
 */
public class ConfluenceTasks {
    private final ConfluenceService service;

    public ConfluenceTasks(ConfluenceService service) {
        this.service = service;
    }

    public ConfluenceService getService() {
        return service;
    }

    public RemotePage getOrCreatePage(long parentId, String title) {
        RemotePage parent = service.getPage(parentId);
        try {
            return service.getPage(parent.getSpace(), title);
        } catch (AtlassianException e) {
            RemotePage newPage = new RemotePage();
            newPage.setSpace(parent.getSpace());
            newPage.setParentId(parent.getId());
            newPage.setTitle(title);
            newPage.setContent("");
            return service.storePage(newPage);
        }
    }
}
