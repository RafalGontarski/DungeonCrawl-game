package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, ActorType type) {
        super(cell, type);
    }

    @Override
    public String getTileName() {
        return String.valueOf(ActorType.SKELETON);
    }
}
