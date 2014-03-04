import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import guru.nidi.atlassian.remote.script.JsConfluence;
import guru.nidi.atlassian.remote.script.RemoteConfluence;
import guru.nidi.atlassian.remote.script.RpcException;

/**
 *
 */
public class RemoteConfluenceTest {
    @Test
    @Ignore
    public void testExecute() throws Exception {
        RemoteConfluence confluence = new RemoteConfluence("https://rune.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
        System.out.println(confluence.getSpaces());
        System.out.println(confluence.getPages("~stni"));
        Map<String, Object> page;
        try {
            page = (Map<String, Object>) confluence.getPage("~stni", "New");
        } catch (RpcException e) {
            page = new HashMap<String, Object>();
            page.put("space", "~stni");
            page.put("parentId", 20317701);
            page.put("title", "New");
            page.put("content", "huhuhhuhuhu");
            page = (Map<String, Object>) confluence.storePage(page);
        }
        page.put("content", ((String) page.get("content")) + "<br>next");
        confluence.updatePage(page, new HashMap<String, Object>());

        JsConfluence js = new JsConfluence();
        js.eval("disp=function(obj){for(p in obj){println(p+'='+obj[p]);}};");
        js.eval("c=new Confluence('https://rune.mimacom.com', '" + System.getenv("JIRA_USER") + "','" + System.getenv("JIRA_PASS") + "');");
        js.eval("println(c.getSpaces());");
        js.eval("println(c.getPages('~stni'));");
        js.eval("try{ page=c.getPage('~stni','New'); }catch(e){page=c.storePage({space:'~stni',parentId:20317701,title:'New',content:'This is new'});};");
        js.eval("page.content+='<br>next line'; try{c.updatePage(page,{});}catch(e){println(e);}");
    }

    @Test
    @Ignore
    public void testScript() throws Exception {
        RemoteConfluence confluence = new RemoteConfluence("https://rune.mimacom.com", System.getenv("JIRA_USER"), System.getenv("JIRA_PASS"));
//        confluence.execute("atlas.getPage('a');");
//        confluence.execute("atlas.getPage(1);");
//        confluence.execute("atlas.getPage([1,2,3]);");
//        confluence.execute("atlas.getPage({a:'b',c:1});");
    }
}
