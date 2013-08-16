package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.Assert.assertEquals;
import static stni.atlassian.remote.queryold.JqlBuilder.*;

/**
 *
 */
public class JqlBuilderTest {
    @Test
    public void testAnd(){
        assertEquals("", and(null));
        assertEquals("", and(""));
        assertEquals(" and (bla)", and("bla"));
    }

    @Test
    public void testOrderBy(){
        assertEquals("", orderBy(null));
        assertEquals("", orderBy(""));
        assertEquals(" order by bla",orderBy("bla"));
    }

    @Test
    public void testProjectEq(){
        final RemoteProject project = new RemoteProject();
        project.setKey("bla");
        assertEquals("project=bla", projectEq(project));
    }

    @Test
    public void testParentEq(){
        final RemoteIssue issue = new RemoteIssue();
        issue.setKey("bla");
        assertEquals("parent=bla", parentEq(issue));
    }

    @Test
    public void testParentIn(){
        assertEquals("parent in ()", parentIn(Collections.<QueryIssue>emptyList()));
        assertEquals("parent in (1)", parentIn(Arrays.asList(issue("1"))));
        assertEquals("parent in (1,2)", parentIn(Arrays.asList(issue("1"),issue("2"))));
    }

    @Test
    public void testLinkedWith(){
        assertEquals("id in linkedissues(key,'type')",linkedWith("key","type"));
        assertEquals("id in linkedissues(key)",linkedWith("key",null));
    }

    private QueryIssue issue(String key) {
        final RemoteIssue issue = new RemoteIssue();
        issue.setKey(key);
        return new QueryIssue(null, issue);
    }
}
