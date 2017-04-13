package eu.suhajko.movie;

import eu.suhajko.movie.genre.Category;
import eu.suhajko.movie.title.Title;
import eu.suhajko.movie.title.TitleConverter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;


/**
 * Created by marek.melis on 4/2/17.
 */
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Movie.withCategories", attributeNodes = @NamedAttributeNode("categories"))
})
@Entity
@Table(name = "movie")
public class Movie extends AbstractPersistable<Long> {

    @Column
    @Convert(converter = TitleConverter.class)
    @NotEmpty(message = "Movies must have some titles")
    private List<Title> titles;

    @Column(name = "titles", updatable = false, insertable = false)
    private String title;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
//    @NotEmpty(message = "Movie must be assigned to some category")
    private List<Category> categories;

    @Column(name = "path_to_movie")
    private String pathToMovie;

    @Column(name = "description")
    private String description;

    @Column(name = "hash", unique = true)
    private String hash;

    public List<Title> getTitles() {
        return titles;
    }

    public Movie setTitles(List<Title> titles) {
        this.titles = titles;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Movie setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public String getPathToMovie() {
        return pathToMovie;
    }

    public Movie setPathToMovie(String pathToMovie) {
        this.pathToMovie = pathToMovie;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movie setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public Movie setHash(String hash) {
        this.hash = hash;
        return this;
    }
}
