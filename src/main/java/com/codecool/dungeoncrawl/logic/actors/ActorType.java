package com.codecool.dungeoncrawl.logic.actors;

public enum ActorType {
    SKELETON("skeleton"),
    PLAYER("player");

    private final String tileName;

    ActorType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
