package eu.suhajko.movies;

import eu.suhajko.AbstractEntityImpl;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;


/**
 * Created by marek.melis on 4/2/17.
 */
@Entity
@Table(name = "MOVIE")
public class Movie extends AbstractPersistable<Long> {
    @Column(name = "DESCRIPTION")
    private String description;

    @Column
    @Convert(converter = TitleConverter.class)
    private List<Title> titles;

    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
