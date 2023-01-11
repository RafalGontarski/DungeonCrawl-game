package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.utils.Config;
import com.codecool.dungeoncrawl.logic.utils.MathHelper;

public class Skeleton extends Actor {
    private Actor actor;
    private Player target;

    private int hp;

    public Skeleton(Cell cell, ActorType actorType) {
        super(cell, actorType);
        super.speed = 2;
        this.hp = getHealth();
    }


    public Skeleton(byte id, int health, Player target) {
        super(id, Config.ENEMY_HP, Config.ENEMY_HIT);
        this.target = target;
        super.speed = 2;
        this.hp = health;
    }

    public void damage(int amount, MathHelper.Direction hit) {
        this.hp -= amount;
        super.x += hit.dirX * 90;
        super.y += hit.dirY * 90;
    }

    @Override
    public String getTileName() {
        return ActorType.SKELETON.getTileName();
    }
}
