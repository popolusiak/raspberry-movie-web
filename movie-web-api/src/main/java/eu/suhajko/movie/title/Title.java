package eu.suhajko.movie.title;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Created by marek.melis on 4/2/17.
 */
public class Title implements Serializable {

    @NotNull(message = "Language is mandatory")
    private Language language;
    @NotNull(message = "Title is mandatory")
    private String name;

    public Language getLanguage() {
        return language;
    }

    public Title setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getName() {
        return name;
    }

    public Title setName(String name) {
        this.name = name;
        return this;
    }
}
