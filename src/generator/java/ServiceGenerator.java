import com.mimacom.jira.rpc.soap.jirasoapservice_v2.JiraSoapService;
import com.mimacom.rune.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapService;
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

/**
 *
 */
public class ServiceGenerator {
    private static final String PACKAGE = "guru.nidi.atlassian.remote";

    private static final Comparator<Method> METHODS_BY_NAME = new Comparator<Method>() {
        @Override
        public int compare(Method m1, Method m2) {
            return m1.getName().compareTo(m2.getName());
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
        final String implName = "Default" + interfaceName;
        PrintWriter writer = createWriter(name, implName);
        writer.println("package " + PACKAGE + "." + name + ";");
        writer.println("import java.net.URL;");
        writer.println("import "+PACKAGE+".AtlassianException;");
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
        writeMethods(writer, serviceClass, "service", "token");
        for (Class<?> delegateClass : delegateClasses) {
            writeMethods(writer, delegateClass, paramName(delegateClass), null);
        }
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
        writeMethodDecls(writer, serviceClass, true);
        for (Class<?> delegateClass : delegateClasses) {
            writeMethodDecls(writer, delegateClass, false);
        }
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

    private void writeMethods(PrintWriter writer, Class<?> serviceClass, String delegate, String firstParam) {
        for (Method m : declaredMethods(serviceClass)) {
            if (Modifier.isPublic(m.getModifiers())) {
                writer.print("  public ");
                writeMethodDecl(writer, m, firstParam != null);
                writeMethodBody(writer, m, delegate, firstParam);
            }
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

    private void writeMethodDecl(PrintWriter writer, Method m, boolean skipFirst) {
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
    }

    private void writeMethodDecls(PrintWriter writer, Class<?> serviceClass, boolean skipFirst) {
        for (Method m : declaredMethods(serviceClass)) {
            if (Modifier.isPublic(m.getModifiers())) {
                writer.print("  ");
                writeMethodDecl(writer, m, skipFirst);
                writer.println(";");
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