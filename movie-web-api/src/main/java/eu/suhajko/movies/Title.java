package eu.suhajko.movies;

import eu.suhajko.AbstractEntityImpl;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by marek.melis on 4/2/17.
 */
@Entity
@Table(name = "TITLE")
public class Title extends AbstractPersistable<Long> {
    @Column(name = "LANGUAGE")
    private String language;
    private String title;
    @Column(name = "TITLE")

    public String getLanguage() {
        return language;
    }

    public Title setLanguage(String language) {
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
