package eu.suhajko.movies;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Created by marek.melis on 4/2/17.
 */
@Converter
public class TitleConverter implements AttributeConverter<Title, String> {
    @Override public String convertToDatabaseColumn(Title title) {
        return String.format("%s|%s", title.getTitle(), title.getLanguage());
    }

    @Override public Title convertToEntityAttribute(String dbData) {

        String[] split = dbData.split("|");

        return new Title().setTitle(split[0])
                .setLanguage(split[0]);
    }
}
