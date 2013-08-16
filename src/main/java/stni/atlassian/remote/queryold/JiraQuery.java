package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stni.atlassian.remote.JiraService;
import stni.atlassian.remote.queryold.expression.ExpressionResolver;
import stni.text.transform.*;
import stni.text.transform.format.SimpleFormatter;
import stni.text.transform.parse.SimpleParser;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public final class JiraQuery {
    private final static Logger LOG = LoggerFactory.getLogger(JiraQuery.class);

    public static final Attribute ISSUE_KEY = Attribute$.MODULE$.apply("issueKey");

    public static class Builder {
        private final QueryService service;
        private int maxResult = 100;
        private String globalExpression;
        private Parser parser = new SimpleParser(new TransformContext(0, Locale.ENGLISH, null));
        private Formatter formatter = new SimpleFormatter();
        private ExpressionResolver resolver;

        public Builder(QueryService service) {
            this.service = service;
        }

        public Builder maxResult(int maxResult) {
            this.maxResult = maxResult;
            return this;
        }

        public Builder globalExpression(String globalExpression) {
            this.globalExpression = globalExpression;
            return this;
        }

        public Builder parser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public Builder formatter(Formatter formatter) {
            this.formatter = formatter;
            return this;
        }

        public Builder resolver(ExpressionResolver resolver) {
            this.resolver = resolver;
            return this;
        }

        public JiraQuery build() {
            return new JiraQuery(service, maxResult, globalExpression, parser, formatter, resolver);
        }
    }

    private final QueryService service;
    private final int maxResult;
    private final String globalExpression;
    private final Parser parser;
    private final Formatter formatter;
    private final ExpressionResolver resolver;

    private JiraQuery(QueryService service, int maxResult, String globalExpression, Parser parser, Formatter formatter, ExpressionResolver resolver) {
        this.service = service;
        this.maxResult = maxResult;
        this.globalExpression = globalExpression;
        this.parser = parser;
        this.formatter = formatter;
        this.resolver = resolver;
    }

    public static Builder builder(JiraService service) {
        return new Builder(new DefaultQueryService(service));
    }

    public static Builder builder(QueryService service) {
        return new Builder(service);
    }

    public QueryList<QueryIssue> issuesOfFilter(String name) {
        return QueryIssueList.ofRemoteIssues(this, service.getIssuesFromFilter(name));
    }

    public QueryList<QueryProject> projects(String... names) {
        return QueryProjectList.ofRemoteProjectList(this, service.getProjectsByKey(names));
    }

    public QueryProject project(String name) {
        return projects(name).get(0);
    }

    public QueryList<QueryProject> allProjects() {
        return QueryProjectList.ofRemoteProjectList(this, service.getProjectsByKey());
    }

    public QueryProject global() {
        return new QueryProject(this, null);
    }

    public QueryList issue(String issueKey) {
        return QueryIssueList.ofRemoteIssueList(this, Collections.singletonList(service.getIssue(issueKey)));
    }

    RemoteIssue[] executeJql(String s) {
        return service.getIssuesFromJqlSearch(s + JqlBuilder.and(globalExpression), maxResult);
    }

    public String parseAndFormat(String s, String issueKey) {
        if (s == null) {
            return "";
        }
        Segment root = parser.parse(s);
        root.addAttribute(ISSUE_KEY, issueKey);
        String formatted = formatter.format(root);
        LOG.trace("Formatting input\n{}\n****** parsed:\n{}\n****** formatted:\n{}\n******", new Object[]{s, root, formatted});
        return formatted;
    }

    public String formatAsPlain(String text) {
        return formatter.format(new Segment(Name.ROOT()).addChild(Segment.plain(text)));
    }

    String getBaseUrl() {
        return service.getBaseUrl();
    }

    String getCustomField(RemoteIssue issue, String name) {
        return service.getCustomField(issue, name);
    }

    ExpressionResolver getResolver() {
        return resolver;
    }

    <T extends QueryObject> QueryList<T> queryListOf(List<T> objects) {
        if (objects.isEmpty()) {
            return (QueryList<T>) QueryIssueList.ofRemoteIssues(this);
        }
        return objects.get(0).newQueryList(this, objects);
    }
}