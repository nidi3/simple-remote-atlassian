package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import org.junit.Ignore;
import org.junit.Test;
import stni.atlassian.remote.queryold.expression.impl.VelocityExpressionResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;

/**
 *
 */
public class QueryListSetMethodsTest {
    private QueryService service = new QueryService() {
        @Override
        public List<RemoteProject> getProjectsByKey(String... keys) {
            return null;
        }

        @Override
        public RemoteIssue getIssue(String issueKey) {
            return null;
        }

        @Override
        public RemoteIssue[] getIssuesFromFilter(String filter) {
            return new RemoteIssue[0];
        }

        @Override
        public RemoteIssue[] getIssuesFromJqlSearch(String query, int maxResults) {
            return new RemoteIssue[0];
        }

        @Override
        public String getBaseUrl() {
            return null;
        }

        @Override
        public String getCustomField(RemoteIssue issue, String name) {
            return null;
        }
    };

    public static class IssueAccessor {
        public String getKey(QueryIssue issue) {
            return issue.getKey();
        }

        public Long getVotes(QueryIssue issue) {
            return issue.getVotes();
        }
    }

    private JiraQuery query;

    private QueryList<QueryIssue> list1;
    private QueryList<QueryIssue> list2;

    private QueryIssue issueA, issueA2, issueB, issueC, issueX;

    public QueryListSetMethodsTest() {
        query = JiraQuery.builder(service).resolver(new VelocityExpressionResolver().withValue("util", new IssueAccessor())).build();
        issueA = issue("a", 1);
        issueA2 = issue("a", 1);
        issueX = issue("a", 17);
        issueB = issue("b", 5);
        issueC = issue("c", 6);
        list1 = new QueryIssueList(query, Arrays.asList(issueA, issueB, issueC));
        list2 = new QueryIssueList(query, Arrays.asList(issueA2, issueX));
    }

    private QueryIssue issue(String key) {
        return issue(key, 0);
    }

    private QueryIssue issue(String key, int votes) {
        final RemoteIssue issue = new RemoteIssue();
        issue.setKey(key);
        issue.setVotes((long) votes);
        return new QueryIssue(query, issue);
    }

    @Test
    @Ignore
    public void testUnion() throws Exception {
        final QueryList<QueryIssue> union = list1.union(list2);
        assertEquals(5, union.size());
        assertTrue(union.contains(issueA));
        assertTrue(union.contains(issueB));
        assertTrue(union.contains(issueC));
        assertTrue(union.contains(issueX));
        assertFalse(union.contains(issue("666")));
    }

    @Test
    @Ignore
    public void testIntersection() throws Exception {
        final QueryList<QueryIssue> intersection = list1.intersection(list2);
        assertEquals(1, intersection.size());
        assertTrue(intersection.contains(issueA));
        assertFalse(intersection.contains(issueB));
    }

    @Test
    @Ignore
    public void testDifference() throws Exception {
        final QueryList<QueryIssue> difference = list1.difference(list2);
        assertEquals(2, difference.size());
        assertFalse(difference.contains(issueA));
        assertTrue(difference.contains(issueB));
        assertTrue(difference.contains(issueC));
    }

    @Test
    @Ignore
    public void testUnique() throws Exception {
        final QueryList<QueryIssue> unique = list1.union(list2).unique();
        assertEquals(4, unique.size());
    }

    @Test
    @Ignore
    public void testFirst() throws Exception {
        assertTrue(list1.first(0).isEmpty());

        final QueryList<QueryIssue> f1 = list1.first(1);
        assertEquals(1, f1.size());
        assertTrue(f1.contains(issueA));

        final QueryList<QueryIssue> f2 = list1.first(2);
        assertEquals(2, f2.size());
        assertTrue(f1.contains(issueA));
        assertTrue(f2.contains(issueB));

        final QueryList<QueryIssue> f5 = list1.first(5);
        assertEquals(3, f5.size());
    }

    @Test
    @Ignore
    public void testLast() throws Exception {
        assertTrue(list1.last(0).isEmpty());

        final QueryList<QueryIssue> f1 = list1.last(1);
        assertEquals(1, f1.size());
        assertTrue(f1.contains(issueC));

        final QueryList<QueryIssue> f2 = list1.last(2);
        assertEquals(2, f2.size());
        assertTrue(f1.contains(issueC));
        assertTrue(f2.contains(issueB));

        final QueryList<QueryIssue> f5 = list1.last(5);
        assertEquals(3, f5.size());
    }

    @Test
    @Ignore
    public void groupBy() {
        final QueryList<QueryIssue> both = list1.union(list2);
        final Map<Object, QueryList<QueryIssue>> groupByKey = both.groupBy("key");
        assertEquals(3, groupByKey.size());
        assertEquals(3, groupByKey.get("a").size());

        final Map<Object, QueryList<QueryIssue>> groupByKeyExpression = both.groupBy("$util.getKey($value)");
        assertEquals(3, groupByKeyExpression.size());
        assertEquals(3, groupByKeyExpression.get("a").size());
    }

    @Test
    @Ignore
    public void aggregate() {
        assertEquals(1.0, issueA.avg("votes"));
        assertEquals(4.0, list1.avg("votes"));
        assertEquals(4.0, list1.avg("$util.getVotes($value)"));

        assertEquals(1.0, list1.min("votes"));
        assertEquals(6.0, list1.max("votes"));

        assertEquals(5.0, list1.median("votes"));
        assertEquals(9.0, list2.median("votes"));

        assertEquals(12.0, list1.sum("votes"));

        assertEquals(7.0, list1.variance("votes"));
        assertEquals(Math.sqrt(7.0), list1.stddev("votes"));
    }
}
