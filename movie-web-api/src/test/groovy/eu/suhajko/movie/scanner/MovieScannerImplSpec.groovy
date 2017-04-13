package eu.suhajko.movie.scanner

import eu.suhajko.movie.movieplayer.exception.MovieWebException
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by marek.melis on 4/12/17.
 */
class MovieScannerImplSpec extends Specification {

    MovieScannerImpl movieScanner = new MovieScannerImpl();

    @Unroll
    def "Load movie #fileName from directory"(fileName){
        given: "Direcotry with movies"
        def movieDirectory = new File("src/test/resources/movies");

        when: "Scan directory for movies"
        def movies = movieScanner.scanForMovies(movieDirectory);

        then: "3 movies should be loaded"
        movies.size() == 3
        and: "Movie gumaco.mp4 should exists"
        def movie = movies.find { it.title == fileName }
        movie != null
        movie.title == fileName
        movie.description == fileName
        movie.hash != ""
        movie.pathToMovie == "${movieDirectory.getAbsolutePath()}/${fileName}".toString();

        where:
        fileName << ["gumaco.mp4", "the_mof_two.mkv", "this_movie_one1.avi"]
    }

    def "Load movies from not existing direcotry"(){
        given: "Not existing directory"
        def movieDirectory = new File("src/test/resources/movie");

        when: "Scan directory for movies"
        def movies = movieScanner.scanForMovies(movieDirectory);

        then: "MovieException thould be thrown"
        thrown MovieWebException
    }

    def "Load movies from empty direcotry"(){
        given: "Not existing directory"
        def movieDirectory = new File("src/test/resources/movies/empty");

        when: "Scan directory for movies"
        def movies = movieScanner.scanForMovies(movieDirectory);

        then: "Empty list of movies should be returned"
        movies.isEmpty()
    }
}
