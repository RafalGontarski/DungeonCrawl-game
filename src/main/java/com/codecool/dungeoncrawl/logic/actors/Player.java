package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.List;

public class Player extends Actor {

    private List<Item> inventory;
    public Player(Cell cell) {
        super(cell);
    }

    public List<Item> getInventory(){
        return inventory;
    }
    public String getTileName() {
        return "player";
    }
}
