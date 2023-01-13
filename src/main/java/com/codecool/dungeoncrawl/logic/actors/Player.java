package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<Item>();
    int damage;

    public Player(Cell cell) {
        super(cell);
        health = 50;
        damage = 5;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getDamage() {
        return damage;
    }

    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (Item item : inventory) {
            itemNames.add(item.getTileName());
        }
        return itemNames;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void checkPickUp() {
        if (this.getCell().getItem() != null) {
            addToInventory(this.getCell().getItem());
            this.increaseStatistics(this.getCell().getItem());
            this.getCell().getItem().removeItemFromMap(this.getCell());
        }
    }

    public void increaseStatistics(Item item) {
        if (item instanceof Weapon) {
            this.damage = this.damage + ((Weapon) item).getDamage();
        } else if (item instanceof Armor) {
            this.health = this.health + ((Armor) item).getHealth();
        }
    }

    public String getTileName() {
        return "player";
    }

//    @Override
//    public void move() {
//
//    }
//
//    @Override
//    public void move(int dx, int dy) {
//        GameMap map = cell.getGameMap();
//        Skeleton enemy = map.getSkeleton();
//        Cell enemyCell = enemy.cell;
////
//////        Door.tryOpen(dx, dy, map, items);
////        //check we have key if yes open door
//        Cell object = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
////        //check object is an obstacle
////        if (map.getObstacles().contains(object.getType())) return;
////        //check is enemies
//        Cell next = cell.getNeighbor(this.getX(), this.getY());
//////        object.getActor() != null
////        if (object.getActor() != null) {
////            if (next.equals(enemyCell)) {
////                fight(enemy);
////            }
////        } else changeCell(dx, dy);//move
//
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if (nextCell.getType() != CellType.WALL) {
//            if (nextCell.getType() != CellType.CLOSEDDOOR) {
//                nextCell.setType(CellType.FLOOR);
//                if (nextCell.getActor() == null) {
//                    cell.setActor(null);
//                    nextCell.setActor(this);
//                    cell = nextCell;
////                    if (object.getActor() != null) {
////                        if (next.equals(enemyCell)) {
////                            fight(enemy);
////                        }
////                    }
//                }
//            }
//        }
//    }

//    public void move(int dx, int dy) {
//        GameMap map = cell.getGameMap();
//        Skeleton skeleton = map.getSkeleton();

//        Cell playerCell = skeleton.cell;
//        Door.tryOpen(dx, dy, map, items);
        //check we have key if yes open door
//        Cell object = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        //check object is an obstacle
//        if (map.getObstacles().contains(object.getType())) return;
        //check is enemies
//        if (object.getActor() != null) {
//
//            Actor enemy = map.getSkeleton();
//            fight(enemy);
//        } else changeCell(dx, dy);//move

//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if (nextCell.getType() != CellType.WALL) {
//            if(nextCell.getType() != CellType.CLOSEDDOOR) {
//                nextCell.setType(CellType.FLOOR);
//                if (nextCell.getActor() == null) {
//                    cell.setActor(null);
//                    nextCell.setActor(this);
//                    cell = nextCell;
//                }
//                else if (nextCell.getActor() != null) {
//                    Skeleton enemy = map.getSkeleton();
//                    fight(enemy);
//                } else changeCell(dx, dy);//move
//                }
//            }
//        }
    }

