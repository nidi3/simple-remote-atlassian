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
package guru.nidi.atlassian.remote;

import com.atlassian.confluence.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapService;
import com.atlassian.jira.rpc.soap.jirasoapservice_v2.JiraSoapService;
import guru.nidi.atlassian.remote.confluence.ConfluenceService;
import guru.nidi.atlassian.remote.confluence.ConfluenceTasks;
import guru.nidi.atlassian.remote.jira.JiraService;
import guru.nidi.atlassian.remote.jira.JiraTasks;
import guru.nidi.atlassian.remote.jira.rest.JiraRestService;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class ServiceGenerator {
    private static final String PACKAGE = "guru.nidi.atlassian.remote";

    private static final Comparator<Method> METHODS_BY_NAME = new Comparator<Method>() {
        @Override
        public int compare(Method m1, Method m2) {
            int res = m1.getName().compareTo(m2.getName());
            if (res == 0) {
                res = m1.getParameterTypes().length - m2.getParameterTypes().length;
            }
            return res;
        }
    };

    public static void main(String[] args) throws IOException {
        ServiceGenerator generator = new ServiceGenerator();
        generator.generateService("/rpc/soap/jirasoapservice-v2", "jira", JiraSoapService.class, "getJirasoapserviceV2", JiraRestService.class, JiraTasks.class);
        generator.generateService("/rpc/soap-axis/confluenceservice-v2", "confluence", ConfluenceSoapService.class, "getConfluenceserviceV2", ConfluenceTasks.class);
    }

    private void generateService(String path, String name, Class<?> serviceClass, String serviceGetter, Class<?>... delegateClasses) throws IOException {
        String interfaceName = Character.toUpperCase(name.charAt(0)) + name.substring(1) + "Service";
        generateInterface(name, interfaceName, serviceClass, delegateClasses);
        generateImpl(path, name, serviceClass, serviceGetter, interfaceName, delegateClasses);
    }

    private void generateImpl(String path, String name, Class<?> serviceClass, String serviceGetter, String interfaceName, Class<?>[] delegateClasses) throws IOException {
        final String implName = "Default" + interfaceName;
        PrintWriter writer = createWriter(name, implName);
        writer.println("package " + PACKAGE + "." + name + ";");
        writer.println("import java.net.URL;");
        writer.println("import " + PACKAGE + ".AtlassianException;");
        writer.println("public class " + implName + " implements " + interfaceName + "{");
        writer.println("  private final String baseUrl;");
        writer.println("  private final String token;");
        writer.println("  private final " + serviceClass.getName() + " service;");
        for (Class<?> delegateClass : delegateClasses) {
            writer.println("  private final " + delegateClass.getName() + " " + paramName(delegateClass) + ";");
        }
        writer.println("  public " + implName + "(String baseUrl, String username, String password){");
        writer.println("    this.baseUrl = baseUrl;");
        writer.println("    try{");
        writer.println("      " + serviceClass.getName() + "ServiceLocator locator = new " + serviceClass.getName() + "ServiceLocator();");
        writer.println("      service = locator." + serviceGetter + "(new URL(baseUrl+\"" + path + "\"));");
        writer.println("      token = service.login(username, password);");
        for (Class<?> delegateClass : delegateClasses) {
            writer.print("      " + paramName(delegateClass) + " = new " + delegateClass.getName() + "(");
            if (hasConstructor(delegateClass, JiraService.class) || hasConstructor(delegateClass, ConfluenceService.class)) {
                writer.print("this");
            } else {
                writer.print("baseUrl, username, password");
            }
            writer.println(");");
        }
        writer.println("    }catch(Exception e){");
        writer.println("      throw new AtlassianException(\"Error initing " + implName + "\",e);");
        writer.println("    }");
        writer.println("  }");
        writer.println("  public String getBaseUrl(){ return baseUrl; }");
        Set<LocalMethod> methods = new HashSet<LocalMethod>();
        for (Class<?> delegateClass : delegateClasses) {
            writeMethods(methods, writer, delegateClass, paramName(delegateClass), null);
        }
        writeMethods(methods, writer, serviceClass, "service", "token");
        writer.println("}");
        writer.close();
    }

    private File targetDirectory(String name) {
        final File dir = new File("src/main/java/" + PACKAGE.replace('.', '/') + "/" + name);
        dir.mkdirs();
        return dir;
    }

    private void generateInterface(String subPackage, String className, Class<?> serviceClass, Class<?>... delegateClasses) throws IOException {
        PrintWriter writer = createWriter(subPackage, className);
        writer.println("package " + PACKAGE + "." + subPackage + ";");
        writer.println("public interface " + className + "{");
        writer.println("  String getBaseUrl();");
        Set<LocalMethod> methods = new HashSet<LocalMethod>();
        for (Class<?> delegateClass : delegateClasses) {
            writeMethodDecls(methods, writer, delegateClass, false);
        }
        writeMethodDecls(methods, writer, serviceClass, true);
        writer.println("}");
        writer.close();
    }

    private PrintWriter createWriter(String subPackage, String className) throws IOException {
        final File file = new File(targetDirectory(subPackage), className + ".java");
        System.out.println("Writing to file " + file);
        return new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
    }

    private boolean hasConstructor(Class<?> clazz, Class<?> parameterTypes) {
        try {
            clazz.getConstructor(parameterTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private void writeMethods(Set<LocalMethod> methods, PrintWriter writer, Class<?> serviceClass, String delegate, String firstParam) {
        for (Method m : declaredMethods(serviceClass)) {
            if (Modifier.isPublic(m.getModifiers())) {
                if (writeMethodDecl(methods, writer, m, firstParam != null)) {
                    writeMethodBody(writer, m, delegate, firstParam);
                }
            }
        }
    }

    private static class LocalMethod {
        private final String name;
        private final Class<?>[] paramTypes;

        private LocalMethod(Method method, boolean ignoreFirst) {
            this.name = method.getName();
            int start = ignoreFirst ? 1 : 0;
            paramTypes = new Class[method.getParameterTypes().length - start];
            System.arraycopy(method.getParameterTypes(), start, paramTypes, 0, paramTypes.length);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            LocalMethod that = (LocalMethod) o;

            return name.equals(that.name)
                    && Arrays.equals(paramTypes, that.paramTypes);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + 17 *
                    (Arrays.hashCode(paramTypes));
        }
    }

    private Method[] declaredMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        Arrays.sort(methods, METHODS_BY_NAME);
        return methods;
    }

    private void writeMethodBody(PrintWriter writer, Method m, String delegate, String firstParam) {
        writer.println("{");
        writer.println("    try{");
        writer.print("      ");
        if (m.getReturnType() != Void.TYPE) {
            writer.print("return ");
        }
        writer.print(delegate + "." + m.getName() + "(");
        if (firstParam != null) {
            writer.print(firstParam);
        }
        int index = 0;
        int skip = firstParam == null ? 0 : 1;
        for (Class<?> param : m.getParameterTypes()) {
            if (index > 0) {
                writer.print(",");
            }
            if (index >= skip) {
                writer.print(paramName(param) + index);
            }
            index++;
        }
        writer.println(");");
        writer.println("    }catch(Exception e){throw new AtlassianException(\"Error calling " + m.getName() + ".\",e);}}");
    }

    private boolean writeMethodDecl(Set<LocalMethod> methods, PrintWriter writer, Method m, boolean skipFirst) {
        final LocalMethod localMethod = new LocalMethod(m, skipFirst);
        if (methods.contains(localMethod)) {
            return false;
        }
        methods.add(localMethod);

        writer.print("public ");
        if (m.getGenericReturnType() instanceof ParameterizedType) {
            writer.print(m.getGenericReturnType());
        } else {
            writer.print(className(m.getReturnType()));
        }
        writer.print(" " + m.getName() + "(");
        int index = 0;
        int skip = skipFirst ? 1 : 0;

        final Class<?>[] paramTypes = m.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> param = paramTypes[i];
            if (index > skip) {
                writer.print(",");
            }
            if (index >= skip) {
                if (m.isVarArgs() && i == paramTypes.length - 1) {
                    writer.print(param.getComponentType().getName() + "...");
                } else {
                    writer.print(className(param));
                }
                writer.print(" " + paramName(param) + index);
            }
            index++;
        }
        writer.print(")");
        return true;
    }

    private void writeMethodDecls(Set<LocalMethod> methods, PrintWriter writer, Class<?> serviceClass, boolean skipFirst) {
        for (Method m : declaredMethods(serviceClass)) {
            if (Modifier.isPublic(m.getModifiers())) {
                writer.print("  ");
                if (writeMethodDecl(methods, writer, m, skipFirst)) {
                    writer.println(";");
                }
            }
        }
    }

    private String paramName(Class<?> clazz) {
        String s = clazz.getSimpleName();
        s = Character.toLowerCase(s.charAt(0)) + s.substring(1);
        while (s.endsWith("[]")) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }

    private String className(Class<?> clazz) {
        return className(clazz, "");
    }

    private String className(Class<?> clazz, String suffix) {
        if (clazz.isArray()) {
            return className(clazz.getComponentType()) + suffix + "[]";
        }
        return clazz.getName();
    }
}