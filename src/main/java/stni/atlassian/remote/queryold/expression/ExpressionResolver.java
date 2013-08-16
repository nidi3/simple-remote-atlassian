package stni.atlassian.remote.queryold.expression;

import java.util.Map;

/**
 *
 */
public interface ExpressionResolver {
    ExpressionResolver withValue(String key, Object value);

    ExpressionResolver withValues(Map<String,Object> values);

    boolean isExpression(String s);

    Object resolve(String e) throws ExpressionException;

}
