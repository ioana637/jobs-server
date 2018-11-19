package com.ubb.jobs.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Level {
    ELEMENTAR("elementar"), INDEPENDENT("independent"), EXPERIMENTAT("experimentat");

    private String levelName;

    Level(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() { return levelName; }
}
