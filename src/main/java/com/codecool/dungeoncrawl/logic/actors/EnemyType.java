package com.codecool.dungeoncrawl.logic.actors;

public enum EnemyType {
    SKELETON("skeleton");

    private final String tileName;

    EnemyType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
