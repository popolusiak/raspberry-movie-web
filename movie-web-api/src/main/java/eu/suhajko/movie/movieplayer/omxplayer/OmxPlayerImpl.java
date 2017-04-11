package eu.suhajko.movie.movieplayer.omxplayer;

import eu.suhajko.command.processor.BashCommandProcessor;
import eu.suhajko.movie.Movie;
import eu.suhajko.movie.movieplayer.MoviePlayer;
import eu.suhajko.movie.movieplayer.PlayerConfiguration;
import eu.suhajko.movie.movieplayer.QueuedMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by marek.melis on 4/11/17.
 */
@Service
public class OmxPlayerImpl implements MoviePlayer {

    private BashCommandProcessor bashCommandProcessor;
    private String command = "omxplayer";
    private ConcurrentLinkedQueue<QueuedMovie> playList;

    @Autowired
    public OmxPlayerImpl(BashCommandProcessor bashCommandProcessor) {
        this.bashCommandProcessor = bashCommandProcessor;
        playList = new ConcurrentLinkedQueue<>();
    }

    @Override public void playMovie(Movie movie, PlayerConfiguration configuration) {
        playList.add(QueuedMovie.instance()
                .setMovie(movie)
                .setPlayerConfiguration(configuration));

        QueuedMovie nowPlaying = playList.peek();

        if(!nowPlaying.getProcess().isPresent()){
            QueuedMovie movieToPlay = playList.poll();
            PlayerConfiguration c = movieToPlay.getPlayerConfiguration();

            StringBuilder sb = new StringBuilder(command);

            c.getPlayAttributes().forEach(a -> sb
                    .append(" ")
                    .append(a.getName())
                    .append(" ")
                    .append(a.getValue())
            );

            sb.append(" ").append(movie.getPathToMovie());

            bashCommandProcessor.execute(sb.toString());
        }



    }

    @Override public void playMovie(List<Movie> movies, PlayerConfiguration configuration) {

    }

    @Override public void dequeMovie(Movie movie) {

    }

    @Override public void dequeMovie(List<Movie> movies) {

    }

    @Override public void forwardTenSeconds() {

    }

    @Override public void backwardTenSeconds() {

    }

    @Override public void pause() {

    }

    @Override public void stop() {

    }

    @Override public Optional<Movie> nowPlaying() {
        return null;
    }

    @Override public void volumeUp() {

    }

    @Override public void volumeDown() {

    }

}
