package com.codecool.dungeoncrawl.logic.mapobjects;

import com.codecool.dungeoncrawl.logic.Cell;

public class ClosedDoor extends Object {
    public ClosedDoor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return SquareType.CLOSED_DOOR.getTileName();
    }
}
