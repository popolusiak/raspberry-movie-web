package eu.suhajko.movies;

import eu.suhajko.AbstractEntityImpl;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;


/**
 * Created by marek.melis on 4/2/17.
 */
@Entity
@Table(name = "GENRE")
public class Genre extends AbstractPersistable<Long> {
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "GENRE_MOVIES", joinColumns = @JoinColumn(name = "GENRE_ID"), inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private List<Movie> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
