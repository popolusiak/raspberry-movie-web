package eu.suhajko.movie;

import eu.suhajko.movie.title.Language;
import eu.suhajko.movie.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * Created by marek.melis on 7/15/17.
 */
@RestController
@RequestMapping(path = "/api")
public class TestResource {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(path = "/testDate", method = RequestMethod.GET)
    public void createTestDate(){

        Title cz = new Title()
                .setLanguage(Language.CZ)
                .setName("Aladin");
        Title en = new Title()
                .setLanguage(Language.EN)
                .setName("Aladin mother fucker");

        Movie movie = new Movie()
                .setPathToMovie("fuckof")
                .setDescription("This is description")
                .setTitle("Aladin")
                .setTitles(Arrays.asList(cz, en));
        List<Movie> all = movieRepository.findAll();
        movieRepository.save(movie);
    }

}
