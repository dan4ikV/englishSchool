package dao;

public class DAOOwnException extends Exception {
    public DAOOwnException() {

    }

    public DAOOwnException(String message) {
        super(message);
    }

    public DAOOwnException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOOwnException(Throwable cause) {
        super(cause);
    }

    protected DAOOwnException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
