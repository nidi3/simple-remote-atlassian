package stni.atlassian.remote.queryold;

/**
 *
 */
public interface QueryObject<T extends QueryObject<T>> extends QueryList<T> {
    Object getField(String field);

    Object resolve(String expression);

    double asDouble(String expression);

}
