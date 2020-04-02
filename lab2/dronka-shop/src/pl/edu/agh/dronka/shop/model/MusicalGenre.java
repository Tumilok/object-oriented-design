package pl.edu.agh.dronka.shop.model;

import java.util.Date;

public enum MusicalGenre {

    RAP("Rap"), POP("Pop"), ROCK("Rock"), DANCE("Dance"), CLASSICAL("Classical");

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    private MusicalGenre(String displayName) {
        this.displayName = displayName;
    }
}
