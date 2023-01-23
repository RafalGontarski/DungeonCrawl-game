package com.codecool.dungeoncrawl.logic.items;

public enum ItemType {
    KEY("key"),
    LIFE_POTION("lifepotion"),
    SWORD("sword");

    private final String tileType;

    ItemType(String tileType) {
        this.tileType = tileType;
    }

    public String getTileName() {
        return tileType;
    }
}
