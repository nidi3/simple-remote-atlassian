package stni.atlassian.remote.query;

import stni.text.transform.Parser;

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

}
