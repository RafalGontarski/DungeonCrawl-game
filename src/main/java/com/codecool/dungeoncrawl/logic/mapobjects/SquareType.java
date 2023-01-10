package com.codecool.dungeoncrawl.logic.mapobjects;

public enum SquareType {
    OPEN_DOOR("open_door"),
    CLOSED_DOOR("closed_door");

    private final String _tileName;

    SquareType(String tileName) {
        this._tileName = tileName;
    }

    public String getTileName() {
        return _tileName;
    }
}
