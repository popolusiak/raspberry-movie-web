package eu.suhajko.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;


/**
 * Created by marek.melis on 4/6/17.
 */
@RepositoryRestResource(exported = true, path = "movies")
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @RestResource(path = "findByTitle")
    @Query(value = "select m from Movie m where m.title like :title%", countQuery = "select count(m) from Movie m where m.title like :title%")
    @EntityGraph(value = "Movie.withCategories")
    Page<Movie> findByTitleStartingWith(@Param("title") String title, Pageable pageable);
}
