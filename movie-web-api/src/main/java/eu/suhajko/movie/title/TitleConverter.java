package eu.suhajko.movie.title;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by marek.melis on 4/2/17.
 */
@Converter
public class TitleConverter implements AttributeConverter<List<Title>, String> {
    @Override public String convertToDatabaseColumn(List<Title> titles) {
        return titles.stream()
                .map(t -> String.format("%s|%s", t.getName(), t.getLanguage()))
                .collect(Collectors.joining("#"));

    }

    @Override public List<Title> convertToEntityAttribute(String dbData) {
        return Stream.of(dbData.split("#"))
                .map(mapToTitle())
                .collect(Collectors.toList());
    }

    private Function<String, Title> mapToTitle() {
        return t -> {
            String[] split = t.split("\\|");
            return new Title().setName(split[0])
                    .setLanguage(Language.valueOf(split[1]));
        };
    }
}
