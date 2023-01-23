package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Enemy extends Actor{
    private EnemyType enemyType;
    public Enemy(Cell cell) {
        super(cell);
    }

    public Enemy(Cell cell, EnemyType enemyType, int damage) {
        super(cell);
        this.enemyType = enemyType;
        this.health = 5;
        this.damage = damage;

    }

    public Enemy(Cell cell, EnemyType enemyType, int health, int damage) {
        super(cell);
        this.enemyType = enemyType;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public String getTileName() {
        if (enemyType == EnemyType.Mage) {
            return EnemyType.Mage.getEnemyName();
        }
        if (enemyType == EnemyType.Skeleton) {
            return EnemyType.Skeleton.getEnemyName();
        }
        return null;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }
}

