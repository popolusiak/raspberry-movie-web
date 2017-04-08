package eu.suhajko.movie.genre;

import eu.suhajko.movie.Movie;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * Created by marek.melis on 4/2/17.
 */
@Entity
@Table(name = "category")
public class Category extends AbstractPersistable<Long> {
    @Column(name = "name")
    @NotNull(message = "Name is mandatory field")
    private String name;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subcategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @ManyToMany
    @JoinTable(name = "category_movies", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
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

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
