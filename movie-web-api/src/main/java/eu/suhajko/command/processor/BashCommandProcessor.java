package eu.suhajko.command.processor;

/**
 * Created by marek.melis on 4/10/17.
 */
public interface BashCommandProcessor {
    Process execute(String command);
    void shutDown(Process process);
}
