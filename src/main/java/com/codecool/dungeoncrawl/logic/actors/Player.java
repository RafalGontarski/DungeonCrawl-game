package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.IncreasingHealth;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.IncreasingDamage;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<Item>();
    int damage;
    private String name;


    public Player(Cell cell) {
        super(cell);
    }
    public Player(Cell cell, int damage) {
        super(cell);
        this.damage = damage;
        this.health = 10;
    }
    public Player(Cell cell, int health, int damage) {
        super(cell);
        this.health = health;
        this.damage = damage;
    }
    public Player(String name, Cell cell, int health, int damage) {
        super(cell);
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }
    public int getDamage(){
        return damage;
    }
    public int getHealth() {
        return health;
    }
    public String getTileName() {
        return "player";
    }
    public List<Item> getInventory(){
        return inventory;
    }
    public List<String> getItemNames(){
        List<String> itemNames = new ArrayList<>();
        for (Item item : inventory)
        {itemNames.add(item.getTileName());
        }
        return itemNames;
    }
    public void setAttribute(List<Item> inventory, int health,int damage){
        this.inventory = inventory;
        this.health = health;
        this.damage = damage;
    }
    public void addToInventory(Item item){
        inventory.add(item);
    }
    public void checkPickUp(){
        if(this.getCell().getItem() != null){
            addToInventory(this.getCell().getItem());
            this.increaseStatistics(this.getCell().getItem());
            this.getCell().getItem().removeItemFromMap(this.getCell());
        }
    }
    public void increaseStatistics(Item item){
        if(item instanceof IncreasingDamage){
            this.damage = this.damage + ((IncreasingDamage) item).getDamage();
        }
        else if(item instanceof IncreasingHealth){
            this.health = this.health + ((IncreasingHealth) item).getHealth();
        }

    }
}
