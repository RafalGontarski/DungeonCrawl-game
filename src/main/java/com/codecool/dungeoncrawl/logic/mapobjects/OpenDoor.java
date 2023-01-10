package com.codecool.dungeoncrawl.logic.mapobjects;

import com.codecool.dungeoncrawl.logic.Cell;

public class OpenDoor extends Object {
    public OpenDoor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return SquareType.OPEN_DOOR.getTileName();
    }
}
