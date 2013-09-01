package stni.atlassian.remote.bamboo.rest;

import stni.atlassian.remote.rest.RestAccess;

/**
 *
 */
public class BambooRestAccess extends RestAccess {
    public BambooRestAccess(String base, String username, String password) {
        super(base + "/rest/api/latest/", username, password);
    }
}