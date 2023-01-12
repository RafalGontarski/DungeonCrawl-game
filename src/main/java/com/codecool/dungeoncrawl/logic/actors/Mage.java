package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mage extends Actor {

    public Mage(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "mage";
    }
}