package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{
    int damage = 5;

    public Sword(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "sword";
    }
}
