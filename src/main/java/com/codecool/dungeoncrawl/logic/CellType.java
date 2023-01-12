package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),

    CLOSEDDOOR("closeddoor"),

    OPENDOOR("opendoor"),

    UPSTAIRS("upstairs"),
    DOWNSTAIRS("downstairs"),

    WALL("stonewall");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
