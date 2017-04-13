package eu.suhajko.movie.scanner;

import eu.suhajko.movie.Movie;

import java.io.File;
import java.util.Set;


/**
 * Created by marek.melis on 4/12/17.
 */
public interface MovieScanner {
    Set<Movie> scanForMovies(File directory);
}
