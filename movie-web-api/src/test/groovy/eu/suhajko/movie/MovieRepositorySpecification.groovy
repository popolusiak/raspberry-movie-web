package eu.suhajko.movie

import com.fasterxml.jackson.databind.ObjectMapper
import eu.suhajko.movie.genre.Category
import eu.suhajko.movie.title.Language
import eu.suhajko.movie.title.Title
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import java.nio.charset.Charset

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MovieRepositorySpecification extends Specification {
    MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf-8"));

    @Autowired
    private MockMvc mockMvc
    private static String urlPath = "/api/movies"

    def "Create movie with all mandatory fields"() {
        given:
        def movie = new ObjectMapper().writeValueAsString([
                titles: [[title: "Grows Ups", language: "EN"], [title: "Dospeláci", language: "SK"]]
        ])

        when: "All mandatory fields are provided"
        def movieResult = mockMvc.perform(post(urlPath)
                .contentType(mediaType)
                .content(movie)
        )

        then: "Response HTTP code should be 201 - Created"
        movieResult.andExpect(status().isCreated());
        and: "Response contains new created movie"
        movieResult.andExpect(jsonPath('$.id',).isNumber());
        movieResult.andExpect(jsonPath('$.titles[0].title').value("Grows Ups"));

    }
}
