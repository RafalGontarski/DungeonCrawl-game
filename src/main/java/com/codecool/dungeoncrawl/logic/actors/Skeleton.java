package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private Actor actor;
    private Player target;
    private int _hp;

    public Skeleton(Cell cell, ActorType actorType) {
        super(cell, actorType);
        super.speed = 2;
        this._hp = 5;
    }

    @Override
    public String getTileName() {
        return ActorType.SKELETON.getTileName();
    }
}
