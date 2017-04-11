package eu.suhajko.command.processor

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * Created by marek.melis on 4/10/17.
 */
@Stepwise
class BashCommandProcessorSpec extends Specification {

    BashCommandProcessorImpl bashCommandProcessor = new BashCommandProcessorImpl();
    @Shared
    Process topProcess;

    def "Run command list all files"() {
        given:
        def command = "ls /dev"
        def expectedFile = "sda"

        when: "Run comman ${command}"
        def process = bashCommandProcessor.execute(command);

        then: "String output should contain file ${expectedFile}"
        process.getInputStream().getText("utf-8").contains(expectedFile);
    }

    def "Start command to see ping to sme.sk usage which needs user interaction to finish"() {
        given:
        def command = "ping sme.sk"
        def expectedFile = "bytes of data"

        when: "Run comman ${command}"
        topProcess = bashCommandProcessor.execute(command);

        then: "String output should contain file ${expectedFile}"
        BufferedReader reader = new BufferedReader(new InputStreamReader(topProcess.getInputStream()));

        def outputString = reader.readLine();
        outputString.contains(expectedFile);
    }

    def "Existing ping process is running "(){
        given:
        def expectedString = "time"
        def waitingTime = 1000

        when: "Sleeping a while ${waitingTime} ms"
        sleep(waitingTime)

        and: "Getting output for running top command "
        BufferedReader reader = new BufferedReader(new InputStreamReader(topProcess.getInputStream()));
        def output = reader.readLine();

        then: "There should exist ${expectedString} on output"
        output.contains(expectedString)
    }

    def "Stopping Existing ping process"(){
        def waitForFinishTime = 500;
        when: "Calling destroy on the process"
        this.topProcess.destroy();

        and: "Sleep wati ${waitForFinishTime}ms"
        sleep(waitForFinishTime)

        then: "Process should be no more alive"
        !topProcess.alive
    }


}
