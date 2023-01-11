package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Random;

public class Skeleton extends Actor {
    private Random random;
    public Skeleton(Cell cell) {
        super(cell);
    }

    public void move() {

        GameMap map = cell.getGameMap();
        Player player = map.getPlayer();
        Cell playerCell = player.cell;

        while(true){

            int[][] coordsDifferentials = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int[] diff = coordsDifferentials[random.nextInt(coordsDifferentials.length)];

            if (!cell.hasNeighbor(diff[0], diff[1])){
                continue;
            }

            Cell next = cell.getNeighbor(diff[0], diff[1]);

            if (next.getActor() == null
                    && !map.getObstacles().contains(next.getType())) {
                changeCell(diff[0], diff[1]);
                break;
            } else if (next.equals(playerCell)){
                fight(player);
                break;
            }
        }
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
