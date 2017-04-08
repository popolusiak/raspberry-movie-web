package eu.suhajko.configuration;

import eu.suhajko.movie.Movie;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


/**
 * Created by marek.melis on 4/8/17.
 */
@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {
    @Override public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Movie.class);
    }
}
