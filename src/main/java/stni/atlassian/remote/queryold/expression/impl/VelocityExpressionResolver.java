package stni.atlassian.remote.queryold.expression.impl;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import stni.atlassian.remote.queryold.expression.ExpressionException;
import stni.atlassian.remote.queryold.expression.ExpressionResolver;

import java.io.StringWriter;
import java.util.Map;

/**
 *
 */
public class VelocityExpressionResolver implements ExpressionResolver {
    private final VelocityEngine engine = new VelocityEngine();
    private final VelocityContext context;

    public VelocityExpressionResolver() {
        this.context = new VelocityContext();
    }

    @Override
    public ExpressionResolver withValue(String key, Object value) {
        context.put(key, value);
        return this;
    }

    @Override
    public ExpressionResolver withValues(Map<String, Object> values) {
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            withValue(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public boolean isExpression(String s) {
        return s.contains("$");
    }

    @Override
    public Object resolve(String s) {
        try {
            final StringWriter out = new StringWriter();
            engine.evaluate(context, out, getClass().getSimpleName(), s);
            return out.toString();
        } catch (VelocityException e) {
            throw new ExpressionException(e);
        }
    }
}
