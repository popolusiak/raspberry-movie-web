package eu.suhajko.movie.title;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Created by marek.melis on 4/2/17.
 */
@Entity
@Table(name = "TITLE")
public class Title extends AbstractPersistable<Long> {

    @Column(name = "LANGUAGE")
    @NotNull(message = "Language is mandatory")
    @Enumerated
    private Language language;
    @NotNull(message = "Title is mandatory")
    @Column(name = "TITLE")
    private String title;

    public Language getLanguage() {
        return language;
    }

    public Title setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Title setTitle(String title) {
        this.title = title;
        return this;
    }
}
