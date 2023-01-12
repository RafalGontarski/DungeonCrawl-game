package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class SkeletonGuard extends Actor {

    public SkeletonGuard(Cell cell) {super(cell);}

    @Override
    public String getTileName() {
        return "skeleton_guard";
    }
}