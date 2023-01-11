package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.utils.MathHelper;

import java.awt.*;

public class Actor extends Rectangle implements Drawable {
    private final ActorType actorType;
    private Cell cell;
    protected int speed;
    protected byte entityID;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;
    private int health = 10;
    protected MathHelper.Direction facing;
    protected byte animationFrame;
    protected byte animationDelay;



    public Actor(Cell cell, ActorType actorType) {
        this.cell = cell;
        this.actorType = actorType;
        this.cell.setActor(this);
        this.speed = 5;
    }


    public Actor(byte id, int posXinRoom, int posYinRoom) {
        super(posXinRoom* Tiles.SIZE, posYinRoom*Tiles.SIZE, Tiles.SIZE, Tiles.SIZE);
        this.entityID = id;
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.speed = 5;
        this.facing = MathHelper.Direction.SOUTH;
        this.animationFrame = 0;
        this.actorType = getActorType();
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;


        if(up) {
            super.y-=this.speed;
            this.facing = MathHelper.Direction.NORTH;
        }
        if(down) {
            super.y+=this.speed;
            this.facing = MathHelper.Direction.SOUTH;
        }
        if(left) {
            super.x-=this.speed;
            this.facing = MathHelper.Direction.WEST;
        }
        if(right) {
            super.x+=this.speed;
            this.facing = MathHelper.Direction.EAST;
        }
    }

    public void setMovingUp(boolean up) {
        this.up = up;
    }

    public void setMovingDown(boolean down) {
        this.down = down;
    }

    public void setMovingLeft(boolean left) {
        this.left = left;
    }

    public void setMovingRight(boolean right) {
        this.right = right;
    }

    public void setCenterX(int x) {
        super.x = x - super.width/2;
    }

    public void setCenterY(int y) {
        super.y = y - super.height/2;
    }

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        cell.setActor(null);
//        nextCell.setActor(this);
//        cell = nextCell;
//    }

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

//    public int getX() {
//        return cell.getX();
//    }
//
//    public int getY() {
//        return cell.getY();
//    }
}
