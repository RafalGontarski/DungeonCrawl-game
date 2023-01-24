package com.codecool.dungeoncrawl.logic.actors;

public enum EnemyType {
    Mage("mage"),
    Skeleton("skeleton");

    private String enemyName;
    EnemyType(String enemyName) {
        this.enemyName = enemyName;
    }
    public String getEnemyName() {
        return enemyName;
    }
}
