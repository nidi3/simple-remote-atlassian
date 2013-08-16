package stni.atlassian.remote.queryold;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface QueryList<T extends QueryObject> extends List<T> {
    JiraQuery getQuery();

    QueryList<T> newQueryList(JiraQuery query, List<T> objects);

    QueryList<QueryIssue> children(String jql);

    QueryList<QueryIssue> children(String jql, String order);

    QueryList<T> linked(String jql);

    QueryList<T> linked(String jql, String order);

    QueryList<T> typedLinked(String linkType, String jql);

    QueryList<T> typedLinked(String linkType, String jql, String order);

    QueryList<T> union(QueryList<T> queryList);

    QueryList<T> intersection(QueryList<T> queryList);

    QueryList<T> difference(QueryList<T> queryList);

    QueryList<T> unique();

    QueryList<T> first(int n);

    QueryList<T> last(int n);

    QueryList<T> sort(Comparator<T> comparator);

    Map<Object, QueryList<T>> groupBy(String expression);

    double avg(String expression);

    double median(String expression);

    double stddev(String expression);

    double variance(String expression);

    double min(String expression);

    double max(String expression);

    double sum(String expression);
}