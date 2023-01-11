package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {


    public Player(Cell cell, ActorType actorType) {
        super(cell, actorType);
    }

    public String getTileName() {
        return ActorType.PLAYER.getTileName();
    }
}
