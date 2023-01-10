package com.codecool.dungeoncrawl.logic.mapobjects;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Object implements Drawable {

    private final Cell _cell;

    public Object(Cell cell) {
        this._cell = cell;
    }

    public Cell getCell() {
        return _cell;
    }
}
