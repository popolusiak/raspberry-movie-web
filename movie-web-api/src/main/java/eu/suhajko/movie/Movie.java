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
@Entity
@Table(name = "movie")
public class Movie extends AbstractPersistable<Long> {


    private String description;

    @Column
    @Convert(converter = TitleConverter.class)
    @NotEmpty(message = "Movies must have some titles")
    private List<Title> titles;

    @Column(name = "titles", updatable = false, insertable = false)
    private String title;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
//    @NotEmpty(message = "Movie must be assigned to some category")
    private List<Category> categories;


    @Override public Long getId() {
        return super.getId();
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
