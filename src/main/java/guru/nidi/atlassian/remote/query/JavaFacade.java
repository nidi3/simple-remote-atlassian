package guru.nidi.atlassian.remote.query;

import scala.Option;
import guru.nidi.text.transform.Parser;
import guru.nidi.text.transform.Segment;

/**
 *
 */
public class JavaFacade {
    private JavaFacade() {
    }

    public static Orderings$ orderings() {
        return Orderings$.MODULE$;
    }

    public static QueryParser queryParser(Parser parser, ParseExceptionHandler exceptionHandler) {
        return new QueryParser(parser, NoOpPreProcessor$.MODULE$, exceptionHandler);
    }

    public static QueryParser throwingQueryParser(Parser parser) {
        return new QueryParser(parser, NoOpPreProcessor$.MODULE$, ThrowingParseExceptionHandler$.MODULE$);
    }

    public static QueryIssueList issueOf(Segment segment) {
        Option<QueryIssueList> issue = QueryParser.issueOf(segment);
        return issue.isEmpty() ? null : issue.get();
    }

}
