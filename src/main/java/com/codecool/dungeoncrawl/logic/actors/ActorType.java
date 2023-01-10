package com.codecool.dungeoncrawl.logic.actors;

public enum ActorType {
    PLAYER("player"),
    SKELETON("skeleton");

    private String _tileName;

    ActorType(String tileName) {
        this._tileName = tileName;
    }

    public String getTileName() {
        return _tileName;
    }
}
