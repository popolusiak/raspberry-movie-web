package eu.suhajko.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by marek.melis on 4/6/17.
 */
@RepositoryRestResource(exported = true, path = "movies")
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
