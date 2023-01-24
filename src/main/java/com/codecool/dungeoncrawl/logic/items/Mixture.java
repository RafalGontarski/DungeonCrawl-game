package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mixture extends Item implements IncreasingHealth{


    private ItemType itemType;
    private int health;

    public Mixture(Cell cell, ItemType itemType) {
        super(cell);
        this.itemType = itemType;
    }
    public Mixture(Cell cell, ItemType itemType, int increasingHealthValue) {
        super(cell);
        this.itemType = itemType;
        this.health = increasingHealthValue;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String getTileName() {
        if (itemType == ItemType.LIFE_POTION) {
            return "lifepotion";
        }
        return null;
    }
}
