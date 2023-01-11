package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    private String _fight;
    int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        GameMap map = cell.getGameMap();
//        Door.tryOpen(dx, dy, map, items);

        Cell nextCell = cell.getNeighbor(dx, dy);
        Cell object = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        if(nextCell.getType() != CellType.WALL){
            if(nextCell.getActor() == null){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else {
                Actor enemy = nextCell.getActor();
                fight(enemy);
            }
        }
    }

    public void changeCell(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public String getFight() {
        return _fight;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void attack(Actor enemy){
        enemy.health -= damage;
        System.out.println(enemy.getClass().getSimpleName() + " : " + enemy.health);
    }

    public void fight(Actor enemy) {
        System.out.println(this.getClass().getSimpleName() + " vs " + enemy.getClass().getSimpleName());
        if (health > 0) attack(enemy);
        if (enemy.health > 0) enemy.attack(this);
    }
}
