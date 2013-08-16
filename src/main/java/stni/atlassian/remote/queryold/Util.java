package stni.atlassian.remote.queryold;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
abstract class Util {
    static Object getField(Object obj, String field) {
        try {
            Method accessor = obj.getClass().getMethod("get" + Character.toUpperCase(field.charAt(0)) + field.substring(1));
            return accessor.invoke(obj);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
