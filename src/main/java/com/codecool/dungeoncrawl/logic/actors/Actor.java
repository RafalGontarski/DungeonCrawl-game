package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.utils.RandomHelper;

import java.awt.*;

public class Actor extends Rectangle implements Drawable {
    protected Cell cell;
    private CellType cellType;
    protected int health = 10;

    protected byte actorID;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;

    protected int speed;

    protected RandomHelper.Direction facing;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public Actor(CellType cellType, int posXinRoom, int posYinRoom) {
        super(posXinRoom* Tiles.TILE_WIDTH, posYinRoom*Tiles.TILE_WIDTH, Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
        this.cellType = cellType;
//        this.actorID = id;
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.speed = 5;
        this.facing = RandomHelper.Direction.SOUTH;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;

        if(up) {
            super.y-=this.speed;
            this.facing = RandomHelper.Direction.NORTH;
        }
        if(down) {
            super.y+=this.speed;
            this.facing = RandomHelper.Direction.SOUTH;
        }
        if(left) {
            super.x-=this.speed;
            this.facing = RandomHelper.Direction.WEST;
        }
        if(right) {
            super.x+=this.speed;
            this.facing = RandomHelper.Direction.EAST;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public double getX() {
        return cell.getX();
    }

    public double getY() {
        return cell.getY();
    }

    @Override
    public ActorType getTileName() {
        return null;
    }
}
