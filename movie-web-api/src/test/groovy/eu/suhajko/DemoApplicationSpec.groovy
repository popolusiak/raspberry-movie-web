package eu.suhajko

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

/**
 * Created by marek.melis on 4/6/17.
 */
@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationSpec extends Specification {

    @Autowired
    WebApplicationContext context

    def "Create configuration"(){

        expect:
        context != null
    }
}
