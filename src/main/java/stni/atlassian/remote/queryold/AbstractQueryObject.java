package stni.atlassian.remote.queryold;

import stni.atlassian.remote.queryold.expression.ExpressionResolver;

/**
 *
 */
abstract class AbstractQueryObject<T extends QueryObject<T>> extends AbstractQueryList<T> implements QueryObject<T> {
    public AbstractQueryObject(JiraQuery query) {
        super(query);
    }

    protected T getElement() {
        return getElements().get(0);
    }

    public Object resolve(String expression) {
        ExpressionResolver resolver = getQuery().getResolver();
        return resolver.isExpression(expression) ? resolver.withValue("value", this).resolve(expression) : getField(expression);
    }

    public double asDouble(String expression) {
        return Double.parseDouble(resolve(expression).toString());
    }
}
