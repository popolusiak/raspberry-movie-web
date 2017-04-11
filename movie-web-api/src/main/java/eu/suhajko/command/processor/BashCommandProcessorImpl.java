package eu.suhajko.command.processor;

import java.io.IOException;


/**
 * Created by marek.melis on 4/10/17.
 */
public class BashCommandProcessorImpl implements BashCommandProcessor {

    @Override public Process execute(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    @Override public void shutDown(Process process) {
        process.destroy();
    }
}
