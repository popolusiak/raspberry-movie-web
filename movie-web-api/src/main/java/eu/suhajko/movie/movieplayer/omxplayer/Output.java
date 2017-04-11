package eu.suhajko.movie.movieplayer.omxplayer;

import eu.suhajko.movie.movieplayer.PlayAttribute;


/**
 * Created by marek.melis on 4/11/17.
 */
public enum Output implements PlayAttribute {
    LOCAL("local");

    private String name = "-o";
    private String value;

    Output(String value) {
        this.value = value;
    }

    @Override public String getName() {
        return name;
    }

    @Override public String getValue() {
        return value;
    }
}
