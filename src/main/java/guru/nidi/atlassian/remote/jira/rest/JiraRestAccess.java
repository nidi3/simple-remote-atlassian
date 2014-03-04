package guru.nidi.atlassian.remote.jira.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import guru.nidi.atlassian.remote.rest.RestAccess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class JiraRestAccess extends RestAccess{
    public static final TypeReference<HashMap<String, ArrayList<IssueLinkType>>> MAP_WITH_LINKTYPES_TYPE_REFERENCE = new TypeReference<HashMap<String, ArrayList<IssueLinkType>>>() {
    };

    public JiraRestAccess(String base, String username, String password) {
        super(base+"/rest/api/2/",username,password);
    }
}