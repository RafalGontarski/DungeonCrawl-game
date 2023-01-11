package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<Item>();
    public Player(Cell cell) {
        super(cell);
    }

    public List<Item> getInventory(){
        return inventory;
    }

    public List<String> getItemNames(){
        List<String> itemNames = new ArrayList<>();
        for (Item item : inventory)
              { itemNames.add(item.getTileName());
        }
        return itemNames;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }
    public void checkPickUp(){
        if(this.getCell().getItem() != null){
            System.out.println(this.getCell().getItem().getTileName());
            addToInventory(this.getCell().getItem());
            this.getCell().getItem().removeItemFromMap(this.getCell());
        }
    }
    public String getTileName() {
        return "player";
    }
}
