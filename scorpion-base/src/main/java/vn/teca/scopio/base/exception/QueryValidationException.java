package vn.teca.scopio.base.exception;

public class QueryValidationException extends Exception {

    public QueryValidationException(String message) {
        super(message);
    }

    public QueryValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
