package eu.suhajko.movie.scanner;

/**
 * Created by marek.melis on 4/13/17.
 */
public class MD5CheckSumException extends Exception {

    public MD5CheckSumException(String message) {
        super(message);
    }

    public MD5CheckSumException(String message, Throwable cause) {
        super(message, cause);
    }

    public MD5CheckSumException(Throwable cause) {
        super(cause);
    }

    public MD5CheckSumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
