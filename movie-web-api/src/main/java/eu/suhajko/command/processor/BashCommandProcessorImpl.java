package eu.suhajko.command.processor;

import eu.suhajko.movie.movieplayer.exception.MovieWebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created by marek.melis on 4/10/17.
 */
@Component
public class BashCommandProcessorImpl implements BashCommandProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override public Process execute(String command) {
        try {
            return Runtime.getRuntime().exec(command);
        }
        catch (IOException e) {
            logger.error("Exception thrown when trying to run command {}", command, e );
            throw new MovieWebException("Exception thrown when trying to run command", e);
        }
    }

    @Override public void shutDown(Process process) {
        process.destroy();
    }
}
