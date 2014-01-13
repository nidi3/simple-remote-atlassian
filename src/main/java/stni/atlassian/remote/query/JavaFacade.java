package stni.atlassian.remote.query;

import scala.Option;
import stni.text.transform.Parser;
import stni.text.transform.Segment;

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
