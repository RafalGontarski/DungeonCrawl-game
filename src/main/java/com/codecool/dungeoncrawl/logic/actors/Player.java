package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        health = 50;
        damage = 20;
    }

    public String getTileName() {
        return "player";
    }


}
