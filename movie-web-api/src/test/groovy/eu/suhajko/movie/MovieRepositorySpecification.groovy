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
@RunWith(Kintups.class)
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
                .content(movie)
        )

        then: "Response HTTP code should be 201 - Created"
        movieResult.andExpect(status().isCreated());
        and: "Response contains new created movie"
        movieResult.andExpect(jsonPath('$.id',).isNumber());
        movieResult.andExpect(jsonPath('$.titles[0].title').value("Grows Ups"));

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

        when: "Search by movie title: ${title}"
        def url = "${urlPath}/search/findByTitle"
        def movieResult = mockMvc.perform(get(url)
                .param("title", title)
                .param("size", "5"));

        then: "Response HTTP code should be 200 - OK"
        movieResult.andExpect(status().isOk())

        and: "Should return page with one movie ${title}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].title').value(title))
    }

    def "Search by title for existing movie with categories"(){
        given:
        def title="Alf"
        def category="Horror"
        def projection = "withCategories"

        when: "Search by movie title: ${title} with projection ${projection}"
        def url = "${urlPath}/search/findByTitle"
        def movieResult = mockMvc.perform(get(url)
                .param("title", title)
                .param("projection", "withCategories")
                .param("size", "5"));

        then: "Response HTTP code should be 200 - OK"
        movieResult.andExpect(status().isOk())

        and: "Should return page with one movie ${title}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].title').value(title))

        and: "Path should contains category ${category}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].categories[0].name').value(category))
    }

    def "Find all movies ordered by title"(){
        given:
        def url = "${urlPath}"
        def first = "Alf";
        def second = "Grows Ups";

        when: "Requesting all movies by ${url} ordered sorted by title"
        def  movieResult = mockMvc.perform(get(url).param("sort", "title,asc"))

        then: "Movies are sorted by title in order ${first}, ${second}"
        movieResult.andExpect(jsonPath('$._embedded.movies[0].titles[0].title').value(first));
    }
}
