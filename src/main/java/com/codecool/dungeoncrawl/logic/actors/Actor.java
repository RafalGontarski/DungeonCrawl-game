package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int damage;
    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

//    public abstract void move();

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if (nextCell.getType() != CellType.WALL) {
//            if(nextCell.getType() != CellType.CLOSEDDOOR) {
//                nextCell.setType(CellType.FLOOR);
//                if (nextCell.getActor() == null) {
//                    cell.setActor(null);
//                    nextCell.setActor(this);
//                    cell = nextCell;
//                }
//            }
//        }
//    }

    public void move(int dx, int dy) {
        GameMap map = cell.getGameMap();
//        Door.tryOpen(dx, dy, map, items);
        //check we have key if yes open door
        Cell playerObject = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        Cell skeletonObject = map.getCell(map.getActor().getX() + dx, map.getActor().getY() + dy);
        //check object is an obstacle
        if (map.getObstacles().contains(playerObject.getType())) return;
        //check is enemies
        if (playerObject.getActor() != null && skeletonObject.getActor() != null) {
            Actor monsterEnemy = playerObject.getActor();
            Actor playerEnemy = skeletonObject.getActor();
            fight(monsterEnemy);
            fight(playerEnemy);
        } else changeCell(dx, dy);//move
    }



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

    public void changeCell(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }
}
