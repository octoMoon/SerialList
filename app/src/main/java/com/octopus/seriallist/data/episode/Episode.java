package com.octopus.seriallist.data.episode;

public class Episode {
    public int id;
    public String name;
    public Boolean viewed;

    public Episode(int id, String name, Boolean viewed) {
        this.id = id;
        this.name = name;
        this.viewed = viewed;
    }

}
