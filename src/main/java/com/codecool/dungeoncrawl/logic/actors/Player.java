package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {
    private CellType cellType;
    private int hp;
    private int maxHp;
    private byte attackTime;

    public Player(Cell cell) {
        super(cell);
    }

//    public Player() {
//        super(getTileName(), posXinRoom, posYinRoom);
//        this.hp = 20;
//        this.maxHp = 20;
//        this.attackTime = 0;
//    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

//    @Override
//    public void move() {
//        if(this.attackTime == 0) {
//            super.move();
//            switch (super.facing) {
//                case NORTH -> super.entityID = Resources.PLAYER_BACK;
//                case SOUTH -> super.entityID = Resources.PLAYER;
//                case WEST -> super.entityID = Resources.PLAYER_LEFT;
//                case EAST -> super.entityID = Resources.PLAYER_RIGHT;
//            }
//        }
//    }

    public ActorType getTileName() {
        return ActorType.PLAYER;
    }
}
