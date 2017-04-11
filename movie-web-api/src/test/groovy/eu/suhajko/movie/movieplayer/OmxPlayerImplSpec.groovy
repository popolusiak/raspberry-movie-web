package eu.suhajko.movie.movieplayer

import eu.suhajko.command.processor.BashCommandProcessor
import eu.suhajko.movie.Movie
import eu.suhajko.movie.movieplayer.omxplayer.OmxAspectMode
import eu.suhajko.movie.movieplayer.omxplayer.OmxPlayerImpl
import eu.suhajko.movie.movieplayer.omxplayer.Output
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by marek.melis on 4/11/17.
 */
class OmxPlayerImplSpec extends Specification {

    @Shared
    OmxPlayerImpl omxPlayer;
    BashCommandProcessor bashCommandProcessor;

    def setup(){
        bashCommandProcessor = Mock(BashCommandProcessor);
        omxPlayer = new OmxPlayerImpl(bashCommandProcessor)
    }

    def "Play movie if no movies are in queue"() {
        def title = "Grouwn Ups";
        def pathToMovie = "/tmp/movies/grown_ups_2010.avi";
        def aspectMode = OmxAspectMode.STRETCH
        def output = Output.LOCAL;
        def expectedCommand = "omxplayer -o ${output.getValue()} --aspect-mode ${aspectMode.getValue()} ${pathToMovie}"

        given: "Movie to play ${title} with path to movie ${pathToMovie}"
        def movieToPlay = new Movie(title: title, pathToMovie: pathToMovie);

        and: "Play configuration aspect mode - ${aspectMode}, output - ${output} "
        def configuration = new PlayerConfiguration()
                .addPlayAttribute(output)
                .addPlayAttribute(aspectMode);


        when: "Play the movies ${movieToPlay.title}"
        omxPlayer.playMovie(movieToPlay, configuration);

        then: "Command should be run ${expectedCommand}"
        1 * bashCommandProcessor.execute(expectedCommand)
    }


}
