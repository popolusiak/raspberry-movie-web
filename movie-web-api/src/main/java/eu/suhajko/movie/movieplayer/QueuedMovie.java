package eu.suhajko.movie.movieplayer;

import eu.suhajko.movie.Movie;

import java.util.Optional;


/**
 * Created by marek.melis on 4/11/17.
 */
public class QueuedMovie {
    private Movie movie;
    private PlayerConfiguration playerConfiguration;
    private Optional<Process> process = Optional.empty();

    public Movie getMovie() {
        return movie;
    }

    public QueuedMovie setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public PlayerConfiguration getPlayerConfiguration() {
        return playerConfiguration;
    }

    public QueuedMovie setPlayerConfiguration(PlayerConfiguration playerConfiguration) {
        this.playerConfiguration = playerConfiguration;
        return this;
    }

    public Optional<Process> getProcess() {
        return process;
    }

    public QueuedMovie setProcess(Optional<Process> process) {
        this.process = process;
        return this;
    }

    public static QueuedMovie instance(){
        return new QueuedMovie();
    }
}
