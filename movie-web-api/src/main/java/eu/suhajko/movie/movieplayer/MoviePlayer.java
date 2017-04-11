package eu.suhajko.movie.movieplayer;

import eu.suhajko.movie.Movie;

import java.util.List;
import java.util.Optional;


/**
 * Created by marek.melis on 4/11/17.
 */
public interface MoviePlayer {
    void playMovie(Movie movie, PlayerConfiguration configuration);
    void playMovie(List<Movie> movies, PlayerConfiguration configuration);
    void dequeMovie(Movie movie);
    void dequeMovie(List<Movie> movies);
    void forwardTenSeconds();
    void backwardTenSeconds();
    void pause();
    void stop();
    Optional<Movie> nowPlaying();
    void volumeUp();
    void volumeDown();


}
