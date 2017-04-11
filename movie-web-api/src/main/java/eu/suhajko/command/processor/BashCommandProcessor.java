package eu.suhajko.command.processor;

import java.io.IOException;


/**
 * Created by marek.melis on 4/10/17.
 */
public interface BashCommandProcessor {
    Process execute(String command) throws IOException;
    void shutDown(Process process);
}
