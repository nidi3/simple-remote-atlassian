package stni.atlassian.remote;

import com.atlassian.jira.rpc.soap.beans.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 */
public class JiraTasksTest {
    private JiraService remoteIssueService = new DummyJiraService() {
        @Override
        public RemoteIssueType[] getIssueTypes() {
            return new RemoteIssueType[]{issueType("a", "na"), issueType("b", "nb")};
        }

        @Override
        public RemoteIssueType[] getSubTaskIssueTypes() {
            return new RemoteIssueType[]{issueType("c", "nc")};
        }

        @Override
        public Object executeGet(String string0) {
            return Arrays.asList(field("a", "na"), field("customfield_b", "nb"), field("customfield_id", "id"), field("customfield_2", "2"), field("customfield_nix", "nix"));
        }

        @Override
        public RemoteNamedObject[] getAvailableActions(String issueKey) {
            return issueKey.equals("key") ? new RemoteNamedObject[]{named("a", "na"), named("b", "nb")} : new RemoteNamedObject[0];
        }

        @Override
        public RemoteStatus[] getStatuses() {
            return new RemoteStatus[]{status("a", "na"), status("b", "nb")};
        }

        @Override
        public RemoteSecurityLevel[] getSecurityLevels(String projectKey) {
            return projectKey.equals("key") ? new RemoteSecurityLevel[]{securityLevel("a")} : new RemoteSecurityLevel[0];
        }

        @Override
        public RemoteVersion[] getVersions(String projectKey) {
            return projectKey.equals("key") ? new RemoteVersion[]{version("1.0")} : new RemoteVersion[0];
        }
    };

    private RemoteIssueType issueType(String id, String name) {
        final RemoteIssueType issueType = new RemoteIssueType();
        issueType.setId(id);
        issueType.setName(name);
        return issueType;
    }

    private Map<String, String> field(String id, String name) {
        final Map<String, String> field = new HashMap<String, String>();
        field.put("id", id);
        field.put("name", name);
        return field;
    }

    private RemoteCustomFieldValue customField(String id, String... values) {
        final RemoteCustomFieldValue fieldValue = new RemoteCustomFieldValue();
        fieldValue.setCustomfieldId(id);
        fieldValue.setValues(values);
        return fieldValue;
    }

    private RemoteNamedObject named(String id, String name) {
        final RemoteNamedObject object = new RemoteNamedObject();
        object.setId(id);
        object.setName(name);
        return object;
    }

    private RemoteStatus status(String id, String name) {
        final RemoteStatus status = new RemoteStatus();
        status.setId(id);
        status.setName(name);
        return status;
    }

    private RemoteSecurityLevel securityLevel(String name) {
        final RemoteSecurityLevel level = new RemoteSecurityLevel();
        level.setName(name);
        return level;
    }

    private RemoteProject project(String key) {
        final RemoteProject project = new RemoteProject();
        project.setKey(key);
        return project;
    }

    private RemoteVersion version(String name) {
        final RemoteVersion version = new RemoteVersion();
        version.setName(name);
        return version;
    }

    private RemoteIssue issue(RemoteCustomFieldValue... customFieldValues) {
        final RemoteIssue issue = new RemoteIssue();
        issue.setCustomFieldValues(customFieldValues);
        return issue;
    }

    private final JiraTasks tasks = new JiraTasks(remoteIssueService);

    @Test
    public void testIssueTypeById() throws Exception {
        assertEquals("a", tasks.issueTypeById("a").getId());
        assertEquals("c", tasks.issueTypeById("c").getId());
        assertNull(tasks.issueTypeById("na"));
        assertNull(tasks.issueTypeById(null));
    }

    @Test
    public void testIssueTypeByName() throws Exception {
        assertEquals("na", tasks.issueTypeByName("na").getName());
        assertEquals("nc", tasks.issueTypeByName("nc").getName());
        assertNull(tasks.issueTypeByName("a"));
        assertNull(tasks.issueTypeByName(null));
    }

    @Test
    public void testFieldName() throws Exception {
        assertNull(tasks.fieldNameById("a"));
        assertNull(tasks.fieldNameById("customfield_a"));
        assertEquals("nb", tasks.fieldNameById("b"));
        assertEquals("nb", tasks.fieldNameById("customfield_b"));
    }

    @Test
    public void testCustomField() throws Exception {
        final RemoteIssue issue = issue(customField("id", "value"), customField("2", "a", "b"), customField("nix"));
        assertEquals("value", tasks.customFieldByName(issue, "id"));
        assertEquals("a", tasks.customFieldByName(issue, "2"));
        assertEquals(null, tasks.customFieldByName(issue, "nix"));
        assertNull(tasks.customFieldByName(issue, "xxx"));
    }


    @Test
    public void testActionIdByName() throws Exception {
        assertNull(tasks.actionByName("bla", "na"));
        assertEquals("a", tasks.actionByName("key", "na").getId());
        assertNull(tasks.actionByName("key", "xxx"));
    }

    @Test
    public void testStatusIdByName() throws Exception {
        assertEquals("a", tasks.statusByName("na").getId());
        assertNull(tasks.statusByName("xxx"));
    }

    @Test
    public void testProgressStatusAction() throws Exception {

    }

    @Test
    public void testSecurityLevelByName() throws Exception {
        assertNull(tasks.securityLevelByName(project("key"), null));
        assertNull(tasks.securityLevelByName(project("xx"), "a"));
        assertEquals("a", tasks.securityLevelByName(project("key"), "a").getName());
    }

    @Test
    public void testGetIssues() throws Exception {

    }

    @Test
    public void testVersion() throws Exception {
        assertNull(tasks.versionByName(project("key"), null));
        assertNull(tasks.versionByName(project("xx"), "1.0"));
        assertEquals("1.0", tasks.versionByName(project("key"), "1.0").getName());

    }

    @Test
    public void testMakeReleaseNotes() throws Exception {

    }

    @Test
    public void testCreateRequirementAndFeature() throws Exception {

    }
}
