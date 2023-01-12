package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    int health = 10;
    int damage;
    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL) {
            if(nextCell.getType() != CellType.CLOSEDDOOR) {
                nextCell.setType(CellType.FLOOR);
                if (nextCell.getActor() == null) {
                    cell.setActor(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                }
            }
        }
    }

    public abstract void move();

    public int getHealth() {
        return health;
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
