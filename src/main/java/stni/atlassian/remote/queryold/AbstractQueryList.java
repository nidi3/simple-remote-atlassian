package stni.atlassian.remote.queryold;

import java.util.*;

/**
 *
 */
abstract class AbstractQueryList<T extends QueryObject> extends AbstractList<T> implements QueryList<T> {
    private final JiraQuery query;
    private final List<T> elements;

    protected AbstractQueryList(JiraQuery query) {
        this.query = query;
        if (!(this instanceof QueryObject)) {
            throw new IllegalArgumentException("This constructor only legal for subclasses of QueryObject");
        }
        this.elements = Collections.singletonList((T) this);
    }

    public AbstractQueryList(JiraQuery query, List<T> elements) {
        this.query = query;
        this.elements = elements == null ? new ArrayList<T>() : elements;
    }

    @Override
    public boolean add(T element) {
        return elements.add(element);
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public int size() {
        return elements.size();
    }

    public QueryList<T> sort(Comparator<T> comparator) {
        List<T> res = new ArrayList<T>(getElements());
        Collections.sort(res, comparator);
        return newQueryList(getQuery(), res);
    }

    protected List<T> getElements() {
        return elements;
    }

    public JiraQuery getQuery() {
        return query;
    }

    public QueryList<QueryIssue> children(String expression) {
        return children(expression, null);
    }

    public QueryList<T> linked(String expression) {
        return linked(expression, null);
    }

    public QueryList<T> linked(String expression, String order) {
        return typedLinked(null, expression, order);
    }

    public QueryList<T> typedLinked(String linkType, String expression) {
        return typedLinked(linkType, expression, null);
    }

    public QueryList<T> union(QueryList<T> queryList) {
        List<T> res = new ArrayList<T>(this);
        res.addAll(queryList);
        return getQuery().queryListOf(res);
    }

    public QueryList<T> intersection(QueryList<T> queryList) {
        List<T> res = new ArrayList<T>();
        for (T issue : this) {
            if (queryList.contains(issue)) {
                res.add(issue);
            }
        }
        return getQuery().queryListOf(res);
    }

    public QueryList<T> difference(QueryList<T> queryList) {
        List<T> res = new ArrayList<T>();
        for (T issue : this) {
            if (!queryList.contains(issue)) {
                res.add(issue);
            }
        }
        return getQuery().queryListOf(res);
    }

    public QueryList<T> unique() {
        List<T> res = new ArrayList<T>();
        int i = 0;
        for (T issue : this) {
            boolean found = false;
            int j = 0;
            for (T inner : this) {
                if (j == i) {
                    break;
                }
                if (inner.equals(issue)) {
                    found = true;
                    break;
                }
                j++;
            }
            if (!found) {
                res.add(issue);
            }
            i++;
        }
        return getQuery().queryListOf(res);
    }

    public QueryList<T> first(int n) {
        List<T> res = new ArrayList<T>();
        int i = 0;
        for (T issue : this) {
            if (i == n) {
                break;
            }
            res.add(issue);
            i++;
        }
        return getQuery().queryListOf(res);
    }

    public QueryList<T> last(int n) {
        List<T> res = new ArrayList<T>();
        int i = 0;
        for (T issue : this) {
            if (i >= size() - n) {
                res.add(issue);
            }
            i++;
        }
        return getQuery().queryListOf(res);
    }

    public Map<Object, QueryList<T>> groupBy(String expression) {
        Map<Object, QueryList<T>> res = new HashMap<Object, QueryList<T>>();
        for (T issue : this) {
            Object key = issue.resolve(expression);
            QueryList<T> list = res.get(key);
            if (list == null) {
                list = newQueryList(getQuery(), null);
                res.put(key, list);
            }
            list.add(issue);
        }
        return res;
    }

    public double sum(String expression) {
        double sum = 0;
        for (T issue : this) {
            sum += issue.asDouble(expression);
        }
        return sum;
    }

    public double avg(String expression) {
        return sum(expression) / size();
    }

    public double min(String expression) {
        double min = Double.MAX_VALUE;
        for (T issue : this) {
            double v = issue.asDouble(expression);
            if (v < min) {
                min = v;
            }
        }
        return min;
    }

    public double max(String expression) {
        double max = Double.MIN_VALUE;
        for (T issue : this) {
            double v = issue.asDouble(expression);
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    public double median(final String expression) {
        ArrayList<T> list = new ArrayList<T>(getElements());
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return Double.compare(o1.asDouble(expression), o2.asDouble(expression));
            }
        });
        int index = list.size() / 2;
        if (list.size() % 2 == 0) {
            return (list.get(index - 1).asDouble(expression) + list.get(index).asDouble(expression)) / 2;
        }
        return list.get(index).asDouble(expression);
    }

    public double variance(String expression) {
        if (size() <= 1) {
            return 0;
        }
        double s = 0, avg = avg(expression);
        for (T issue : this) {
            double d = issue.asDouble(expression) - avg;
            s += d * d;
        }
        return s / (size() - 1);
    }

    public double stddev(String expression) {
        return Math.sqrt(variance(expression));
    }

}
