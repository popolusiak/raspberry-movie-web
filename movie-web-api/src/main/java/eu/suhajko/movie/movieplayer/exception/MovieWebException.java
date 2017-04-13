package eu.suhajko.movie.movieplayer.exception;

/**
 * Created by marek.melis on 4/11/17.
 */
public class MovieWebException extends RuntimeException {

    public MovieWebException(String message) {
        super(message);
    }

    public MovieWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieWebException(Throwable cause) {
        super(cause);
    }

    public MovieWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
