package eu.suhajko.movie

import com.fasterxml.jackson.databind.ObjectMapper
import eu.suhajko.Kintups
import org.hamcrest.Matchers
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.JsonPathResultMatchers
import spock.lang.Specification

import java.nio.charset.Charset

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
                titles: [[name: "Grows Ups", language: "EN"], [name: "Dospeláci", language: "SK"]]
        ])

        when: "All mandatory fields are provided"
        def movieResult = mockMvc.perform(post(urlPath)
                .content(movie)
        )

        then: "Response HTTP code should be 201 - Created"
        movieResult.andExpect(status().isCreated());
        and: "Response contains new created movie"
        movieResult.andExpect(jsonPath('$.id',).isNumber());
        movieResult.andExpect(jsonPath('$.titles[0].name').value("Grows Ups"));

    }

    def "Find existing movie by id"(){
        given:
        def existingId = 1l;
        def url = "${urlPath}/${existingId}"

        when: "Search by id ${url}"
        def movieResult = mockMvc.perform(get(url))

        then: "Should return movie with id ${existingId}"
        movieResult.andExpect(jsonPath('$.id').value(existingId))
    }

    def "Search by title for existing movie"(){
        given:
        def title="Alf"
        def category="Horror"

        when: "Search by movie name: ${title}"
        def url = "${urlPath}/search/findByTitle"
        def movieResult = mockMvc.perform(get(url)
                .param("title", title)
                .param("size", "2"));

        then: "Response HTTP code should be 200 - OK"
        movieResult.andExpect(status().isOk())

        and: "Should return page with one movie ${title}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].name').value(title))
    }

    def "Search by title for existing movie with categories"(){
        given:
        def title="A"
        def category="Horror"
        def projection = "withCategories"

        when: "Search by movie name: ${title} with projection=${projection}"
        def url = "${urlPath}/search/findByTitle"
        def movieResult = mockMvc.perform(get(url)
                .param("title", title)
                .param("projection", "withCategories")
                .param("page", "0")
                .param("size", "2"));

        then: "Response HTTP code should be 200 - OK"
        movieResult.andExpect(status().isOk())

        and: "Should return page with one movie ${title}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].name').value(title))

        and: "Path should contains category ${category}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].categories[0].name').value(category))
    }

    def "Find all movies ordered by title"(){
        given:
        def url = "${urlPath}"
        def first = "Alf";
        def second = "Grows Ups";

        when: "Requesting all movies by ${url} ordered sorted by name"
        def  movieResult = mockMvc.perform(get(url).param("sort", "name,asc"))

        then: "Movies are sorted by name in order ${first}, ${second}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].name').value(first));
    }

    def "Find all movies order by tytle with projection"(){
        given:
        def url = "${urlPath}"
        def first = "Alf";
        def second = "Grows Ups";
        def projection = "withCategories";

        when: "Requesting all movies by ${url} ordered sorted by name"
        def  movieResult = mockMvc.perform(get(url)
                .param("projection", projection)
                .param("sort", "title,asc"))

        then: "Movies are sorted by name in order ${first}, ${second}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].name').value(first));
    }
}
