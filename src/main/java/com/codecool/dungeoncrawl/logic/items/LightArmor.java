package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class LightArmor extends Item implements Armor{
    int health = 5;

    public LightArmor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "lightArmor";
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}
