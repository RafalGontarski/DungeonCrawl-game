package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Equipment extends Item implements IncreasingDamage{

    private ItemType itemType;
    private int damage;

    public Equipment(Cell cell, ItemType itemType) {
        super(cell);
        this.itemType = itemType;
    }
    public Equipment(Cell cell, ItemType itemType, int increasingDamageValue) {
        super(cell);
        this.itemType = itemType;
        this.damage = increasingDamageValue;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getTileName() {
        if (itemType == ItemType.SWORD){
            return "sword";
        }
        if (itemType == ItemType.KEY){
            return "key";
        }

        return null;
    }
}
