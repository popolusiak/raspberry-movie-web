package eu.suhajko.movie.movieplayer.omxplayer;

import eu.suhajko.movie.movieplayer.PlayAttribute;


/**
 * Created by marek.melis on 4/11/17.
 */
public enum OmxAspectMode implements PlayAttribute {

    STRETCH("stretch");

    private String name = "--aspect-mode";
    private String value;



    OmxAspectMode(String value){
        this.value = value;
    }

    public String getName(){
        return name;
    }
    public String getValue(){
        return value;
    }

}
