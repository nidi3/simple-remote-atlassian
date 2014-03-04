package guru.nidi.atlassian.remote.query

/**
 *
 */
class QueryException(val query: String, cause: Throwable) extends RuntimeException("Problem executing '" + query + "': " + cause.getMessage, cause) {
}