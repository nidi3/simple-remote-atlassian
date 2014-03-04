package guru.nidi.atlassian.remote.script;

import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;
import sun.org.mozilla.javascript.internal.Scriptable;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JsConfluence {
    private static final String CONFLUENCE_CTOR = "Confluence";
    private static final String JAVA_CTOR = "JavaConfluence";
    private static final String PROXY_CTOR = "ProxyConfluence";
    private static final String PRINTABLE_OBJ = "printable";

    private final ScriptEngine js;
    private RemoteConfluence confluence;
    Scriptable printable;

    public JsConfluence() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        js = manager.getEngineByExtension("js");
        if (js == null) {
            throw new IllegalStateException("Rhino scripting engine not found");
        }
        js.setBindings(new SimpleBindings(), ScriptContext.ENGINE_SCOPE);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put(JAVA_CTOR, this);
        List<String>methods=new ArrayList<String>();
        for (Method m : RemoteConfluence.class.getDeclaredMethods()) {
            if (m.getReturnType() == Object.class && Modifier.isPublic(m.getModifiers())) {
                methods.add(m.getName());
            }
        }
        registerMethods(methods.toArray(new String[methods.size()]));

        js.eval(CONFLUENCE_CTOR + "=function(url,username,password){this.init(url,username,password);}; " + CONFLUENCE_CTOR + ".prototype=new " + PROXY_CTOR + "();");

        //TODO recursion vermeiden
        printable = (Scriptable) js.eval(PRINTABLE_OBJ + "={ toString:function(){" +
                "props=function(obj,own){" +
                "  s='';" +
                "  for(p in obj){" +
                "    if (obj.hasOwnProperty(p)===own){" +
                "      s+=(p+'='+obj[p]+'\\n');" +
                "    }" +
                "  }" +
                "  return s;" +
                "};" +
                "return '{'+props(this,true)+'}, prototype {'+props(this,false)+'}';" +
                "}};");
    }

    public void init(String serverUrl, String username, String password) {
        confluence = new RemoteConfluence(serverUrl, username, password);
    }

    public Object eval(String code) throws ScriptException {
        return js.eval(code);
    }

    private Method findUniqueMethod(Class<?> clazz, String name) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    public Object execute(String name, Object... parameters) throws RpcException {
        Method method = findUniqueMethod(RemoteConfluence.class, name);
        Object[] javaParameters = new Object[method.getParameterTypes().length];
        for (int i = 0; i < Math.min(parameters.length, javaParameters.length); i++) {
            javaParameters[i] = fromNativeJavascript(parameters[i]);
        }
        try {
            Object result = method.invoke(confluence, javaParameters);
            return toNativeJavascript(result);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof RpcException) {
                throw (RpcException) e.getCause();
            }
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object toNativeJavascript(Object value) {
        if (value instanceof Map) {
            NativeObject res = new NativeObject();
            res.setPrototype(printable);
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) value).entrySet()) {
                res.defineProperty(entry.getKey(), toNativeJavascript(entry.getValue()), NativeObject.EMPTY);
            }
            return res;
        }
        if (value instanceof List) {
            List<Object> list = (List<Object>) value;
            NativeArray res = new NativeArray(list.size());
            res.setPrototype(printable);
            for (int i = 0; i < list.size(); i++) {
                res.put(i, res, toNativeJavascript(list.get(i)));
            }
            return res;
        }
        return value;
    }

    private Object fromNativeJavascript(Object value) {
        if (value instanceof NativeObject) {
            NativeObject nat = (NativeObject) value;
            HashMap<String, Object> map = new HashMap<String, Object>();
            for (Object id : nat.getAllIds()) {
                String key = (String) id;
                map.put(key, fromNativeJavascript(nat.get(key, nat)));
            }
            return map;
        }
        if (value instanceof NativeArray) {
            NativeArray nat = (NativeArray) value;
            List<Object> res = new ArrayList<Object>();
            int i = 0;
            for (Object id : nat.getAllIds()) {
                int key = (Integer) id;
                while (i < key) {
                    res.add(null);
                    i++;
                }
                res.add(fromNativeJavascript(nat.get(key, nat)));
            }
            return res;
        }
        return value;
    }

    private void registerMethods(String... names) throws ScriptException {
        String script = PROXY_CTOR + "=function(){ self=this;";
        script += "__call=function(name,a){" +
                "args=[];" + "" +
                "for(i=0;i<a.length;i++){" +
                "  args[i]=a[i]};" +
                "  try{" +
                "    return self.execute.call(self,name,args);" +
                "  }catch(e){" +
                "println(e);" +
                "    throw e.rhinoException.wrappedException.errorResponse;" +
                "  }" +
                "};";
        for (String name : names) {
            script += "self." + name + "=function(){return __call('" + name + "',arguments);};";
        }
        script += "}; " + PROXY_CTOR + ".prototype=" + JAVA_CTOR + ";";
        js.eval(script);
    }

}
