package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, ActorType actorType) {
        super(cell, actorType);
    }

    @Override
    public String getTileName() {
        return ActorType.SKELETON.getTileName();
    }
}
