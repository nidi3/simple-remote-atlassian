package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static stni.atlassian.remote.queryold.JqlBuilder.linkedWith;
import static stni.atlassian.remote.queryold.JqlBuilder.parentEq;

/**
 *
 */
public class QueryIssue extends QueryIssueWrapper implements QueryObject<QueryIssue> {
    private final static Logger LOG = LoggerFactory.getLogger(QueryIssue.class);

    QueryIssue(JiraQuery query, RemoteIssue issue) {
        super(query, issue);
    }

    @Override
    public QueryList<QueryIssue> newQueryList(JiraQuery query, List<QueryIssue> objects) {
        return new QueryIssueList(query, objects);
    }

    public QueryList<QueryIssue> children(String expression, String order) {
        return QueryIssueList.ofJql(getQuery(), parentEq(getIssue()), expression, order);
    }

    public QueryList<QueryIssue> typedLinked(String linkType, String expression, String order) {
        return QueryIssueList.ofJql(getQuery(), linkedWith(getKey(), linkType), expression, order);
    }

    public Object getField(String field) {
        return Util.getField(getIssue(), field);
    }

    @Override
    public String getSummary() {
        return getQuery().formatAsPlain(super.getSummary());
    }

    public String getDescription() {
        return getQuery().parseAndFormat(super.getDescription(), getKey());
    }

    public String getUrl() {
        return getQuery().getBaseUrl() + "/browse/" + getKey();
    }

    public String getFormattedCustomField(String name) {
        String raw = getCustomField(name);
        String formatted = getQuery().parseAndFormat(raw, getKey());
        LOG.trace("Issue {}: Custom field\n{}\n****** value:\n{}\n****** formatted:\n{}\n******", new String[]{getKey(), name, raw, formatted});
        return formatted;
    }

    public String getCustomField(String name) {
        return getQuery().getCustomField(getIssue(), name);
    }

}