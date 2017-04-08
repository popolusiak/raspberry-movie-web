package eu.suhajko.movie;

import eu.suhajko.movie.genre.Category;
import eu.suhajko.movie.title.Title;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;


/**
 * Created by marek.melis on 4/8/17.
 */
@Projection(name = "withCategories", types = {Movie.class})
public interface SimpleMovieProjection {
    Long getId();
    List<Title> getTitles();
    String getDescription();
    List<Category> getCategories();
}
