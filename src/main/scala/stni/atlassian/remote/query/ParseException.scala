package stni.atlassian.remote.query

/**
 *
 */
class ParseException(val issue: QueryIssueList, cause: Throwable) extends RuntimeException("Problem parsing " + issue + ": '" + cause.getMessage, cause) {
}