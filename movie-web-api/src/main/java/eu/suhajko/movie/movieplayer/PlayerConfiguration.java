package eu.suhajko.movie.movieplayer;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Created by marek.melis on 4/11/17.
 */
public class PlayerConfiguration {
    private Set<PlayAttribute> playAttributes;
    private Optional<File> subtitle;

    public PlayerConfiguration addPlayAttribute(PlayAttribute playAttribute) {
        if (playAttributes == null) {
            playAttributes = new LinkedHashSet<>();
        }
        playAttributes.add(playAttribute);
        return this;
    }

    public Optional<File> getSubtitle() {
        return subtitle;
    }

    public PlayerConfiguration setSubtitle(Optional<File> subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public Set<PlayAttribute> getPlayAttributes() {
        return playAttributes == null ? Collections.emptySet() : playAttributes;
    }

    public PlayerConfiguration setPlayAttributes(Set<PlayAttribute> playAttributes) {
        this.playAttributes = playAttributes;
        return this;
    }
}
