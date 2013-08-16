package stni.atlassian.remote.queryold;

import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.atlassian.jira.rpc.soap.beans.RemoteProject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Ignore;
import org.junit.Test;
import scala.Option;
import scala.Some;
import stni.text.transform.ResourceLoader;
import stni.text.transform.Segment;
import stni.text.transform.TransformContext;
import stni.text.transform.format.latex.LatexFormatter;
import stni.text.transform.parse.wiki.creole.CreoleWikiParser;

import java.io.*;
import java.util.*;

/**
 *
 */
public class LatexTest {
    @Test
    @Ignore
    public void template2() throws IOException {
        final TransformContext transformContext = new TransformContext(3, Locale.GERMAN, new ResourceLoader() {
            public Option<String> loadResource(Segment source, String name) {
                String issueKey = (String) source.root().attributes().get(JiraQuery.ISSUE_KEY).get();
                System.out.println(issueKey);
                return new Some<String>(issueKey);
            }
        });
        JiraQuery jq = JiraQuery.builder(new MyQueryService())
                .parser(new CreoleWikiParser(transformContext))
                .formatter(new LatexFormatter(transformContext))
                .build();

        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
        engine.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getCanonicalName());
        engine.init();

        Template template = engine.getTemplate("template2.tex");
        VelocityContext context = new VelocityContext();

        context.put("jq", jq);
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("target/out2.tex"), "utf-8");
        template.merge(context, out);
        out.close();
    }

    @Test
    @Ignore
    public void generate() throws IOException, InterruptedException {
        copy(getClass().getClassLoader().getResourceAsStream("WS+1.JPG"), new FileOutputStream(new File("target/WS+1.JPG")));
        generateImpl(new File("target", "out2.tex"));
    }

    private void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[10000];
        int read;
        while ((read = in.read(buf)) > 0) {
            out.write(buf, 0, read);
        }
        out.close();
    }

    private void generateImpl(File file) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("bash", "-l", "-c", "pdflatex -interaction=batchmode " + file.getName())
                .directory(file.getParentFile())
                .redirectErrorStream(true)
                .start();
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (in.ready()) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        process.waitFor();
    }

    private static class MyQueryService implements QueryService {
        private Map<String, String> descMap = new HashMap<String, String>();

        private MyQueryService() {
            descMap.put("1", "First **bold** \\\\ newline\n =heading\nSubtext\n==sub==text\n===subsub===text\n====fourth====text");
            descMap.put("2", "Sec //italics// Issue [[4]] or [[http://www.google.com]] or [[http://google.com|Google]] or http://google.ch");
            descMap.put("3", "Th **BBBB //IIIII// BBBBB** \n*first bla bla \\\\more bla\n* second\n\n {{image|desc}}");
            descMap.put("4", "Numbered:\n# hula \n#balu \n#and more \n\nUnd weiter im Text.");
            descMap.put("5", "|=login|=password\n|bla|blu|\n|aasjajsahsa|kfjdslfkjsdlfkjsdflksdj|\nnext ---- next");
        }

        public List<RemoteProject> getProjectsByKey(String... keys) {
            return Collections.singletonList(new RemoteProject("1", "demo", "desc", null, "demo", "me", null, null, "http://demo", "http://demo"));
        }

        public RemoteIssue getIssue(String issueKey) {
            return new RemoteIssue("1", null, null, null, null, null, null, descMap.get(issueKey), null, null, null, issueKey, null, "demo", null, null, null, "summary", null, null, null);
        }

        @Override
        public RemoteIssue[] getIssuesFromFilter(String filter) {
            return new RemoteIssue[0];
        }

        public RemoteIssue[] getIssuesFromJqlSearch(String query, int maxResults) {
            return new RemoteIssue[]{
                    getIssue("1"), getIssue("2"), getIssue("3"), getIssue("4"), getIssue("5")
            };
        }

        public String getBaseUrl() {
            return "http://dummy";
        }

        public String getCustomField(RemoteIssue issue, String name) {
            return "";
        }
    }
}
