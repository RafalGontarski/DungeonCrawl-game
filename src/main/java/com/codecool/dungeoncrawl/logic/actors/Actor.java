package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.utils.MathHelper;

import java.awt.*;

public abstract class Actor implements Drawable {
    private final ActorType actorType;
    private Cell cell;
    protected int speed;
    private int health = 10;

    public Actor(Cell cell, ActorType actorType) {
        this.cell = cell;
        this.actorType = actorType;
        this.cell.setActor(this);
        this.speed = 5;
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
    public String getTileName() {
        return actorType.getTileName();
    }

    public int getHealth() {
        return health;
    }

    public ActorType getActorType() {
        return actorType;
    }

    public Cell getCell() {
        return cell;
    }

}
