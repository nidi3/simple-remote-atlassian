/*
 * Copyright (C) 2013 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.junit.Ignore;
import org.junit.Test;
import sun.org.mozilla.javascript.internal.NativeObject;
import sun.org.mozilla.javascript.internal.Scriptable;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class RhinoTest {
    public static class Person {
        private String name = "Hans";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    @Ignore
    public void bindingTest() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine js = manager.getEngineByExtension("js");
        js.setBindings(new SimpleBindings() {
            @Override
            public Object get(Object key) {
                // System.out.println(key);
                return super.get(key);
            }
        }, ScriptContext.ENGINE_SCOPE);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("out", System.out);
        NativeObject obj = new NativeObject() {
            @Override
            public Object get(String s, Scriptable scriptable) {
                return super.get(s, scriptable);    //To change body of overridden methods use File | Settings | File Templates.
            }
        };
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("person", new Person());
        Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[]{Serializable.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("proxy", proxy);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("obj", obj);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("str", "hallo");
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "b");
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("map", map);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("dyn", new HashMap<String, Object>() {
            @Override
            public boolean containsKey(Object key) {
                return super.containsKey(key);    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override
            public int size() {
                return super.size();    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override
            public Set<Map.Entry<String, Object>> entrySet() {
                return super.entrySet();    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override
            public Object get(Object key) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        Object eval = js.eval("val={a:1,b:2}");
        js.eval("out.println(proxy.name)");
        js.eval("out.println(person.name)");
        js.eval("out.println(obj.a)");
        js.eval("out.println('hello')");
        js.eval("out.println(str.substring(1,2))");
        js.eval("out.println(map.get('a'))");
        js.eval("out.println(dyn.a)");
    }

    @Test
    @Ignore
    public void invocableTest() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngineFactory scriptEngineFactory = manager.getEngineFactories().get(0);
        System.out.println(scriptEngineFactory.getOutputStatement("aaaa"));
        System.out.println(scriptEngineFactory.getMethodCallSyntax("obj", "meth", "a", "b"));
        System.out.println(scriptEngineFactory.getProgram("obj", "meth"));
        ScriptEngine js = manager.getEngineByExtension("js");
        ScriptContext context = js.getContext();
        context.setBindings(new SimpleBindings(), ScriptContext.ENGINE_SCOPE);
        context.getBindings(ScriptContext.ENGINE_SCOPE).put("X", "X");
        context.setAttribute("W", "W", ScriptContext.ENGINE_SCOPE);
        js.setBindings(new SimpleBindings(), ScriptContext.ENGINE_SCOPE);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("fun", new Object() {
            public void call() {
                System.out.println();
            }

            public void call(Object[] param) {
                System.out.println(param);
            }
        });
        js.eval("call=function(){args=[]; for(i=0;i<arguments.length;i++){args[i]=arguments[i]}; fun.call.call(fun,args);}; call(); ");
    }

    public static class Functions {
        public void exec(String name, Object... params) {
            System.out.println(name + "(" + Arrays.toString(params) + ")");
        }

        public void single(NativeObject obj) {
            System.out.println(obj.get("a", null));
            System.out.println(obj.getIds());
        }
    }

    @Test
    @Ignore
    public void argsTest() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine js = manager.getEngineByExtension("js");
        js.setBindings(new SimpleBindings(), ScriptContext.ENGINE_SCOPE);
        js.getBindings(ScriptContext.ENGINE_SCOPE).put("fun", new Functions());
        js.eval("call=function(name,a){args=[]; for(i=0;i<a.length;i++){args[i]=a[i]}; fun.exec.call(fun,name,args);};");
        js.eval("base={}");
        js.eval("base.func1=function(){call('func1',arguments);};");
        js.eval("base.func2=function(){call('func2',arguments);};");
        js.eval("base.func1()");
        js.eval("base.func1('a')");
        js.eval("base.func1(1,'a')");
        js.eval("base.func2()");
        js.eval("base.func2('a')");
        js.eval("base.func2(1,'a')");
        js.eval("fun.single({a:1,b:'c'});");
    }
}