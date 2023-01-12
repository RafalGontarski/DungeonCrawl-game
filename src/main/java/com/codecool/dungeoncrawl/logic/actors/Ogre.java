package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ogre extends Actor {
    public Ogre(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }
}