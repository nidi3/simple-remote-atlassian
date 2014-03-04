package guru.nidi.atlassian.remote.bamboo.rest;

import guru.nidi.atlassian.remote.rest.RestAccess;

/**
 *
 */
public class BambooRestAccess extends RestAccess {
    public BambooRestAccess(String base, String username, String password) {
        super(base + "/rest/api/latest/", username, password);
    }
}