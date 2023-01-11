package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<Item>();

    int damage = 5;
    public Player(Cell cell) {
        super(cell);
        health = 50;
        damage = 20;
    }
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getType() != CellType.WALL){
            if(nextCell.getActor() == null){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        }
    }

    @Override
    public void move() {

    }

    public List<Item> getInventory(){
        return inventory;
    }

    public int getDamage(){
        return damage;
    }

    public List<String> getItemNames(){
        List<String> itemNames = new ArrayList<>();
        for (Item item : inventory)
            {itemNames.add(item.getTileName());
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
            this.increaseDamage();
            this.getCell().getItem().removeItemFromMap(this.getCell());
        }
    }

    public void increaseDamage() {
        for(Item item: inventory) {
            if(item instanceof Weapon){
                this.damage = this.damage + ((Weapon) item).getDamage();
            }
        }
    }

    public String getTileName() {
        return "player";
    }

}
