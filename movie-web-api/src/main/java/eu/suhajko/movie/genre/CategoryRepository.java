package eu.suhajko.movie.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by marek.melis on 4/8/17.
 */
@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
