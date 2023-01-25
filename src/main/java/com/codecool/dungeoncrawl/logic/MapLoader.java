package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
//import com.codecool.dungeoncrawl.logic.items.Lifepotion;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(Main main, String filename) {


        InputStream is = MapLoader.class.getResourceAsStream(filename);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY,main);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> cell.setType(CellType.EMPTY);
                        case '#' -> cell.setType(CellType.STONEWALL);
                        case '.' -> cell.setType(CellType.FLOOR);
                        case 'c' -> cell.setType(CellType.CLOSEDDOOR);
                        case 'd' -> cell.setType(CellType.OPENDOOR);
                        case '^' -> cell.setType(CellType.UPSTAIRS);
                        case 'v' -> cell.setType(CellType.DOWNSTAIRS);
                        case '%' -> cell.setType(CellType.MAPLEWALL);
                        case 'w' -> cell.setType(CellType.MAPLEWINDOW);
                        case 't' -> cell.setType(CellType.TORCH);
                        case '+' -> cell.setType(CellType.STONEFLOOR);
                        case 's' -> {
                            cell.setType(CellType.FLOOR);
                            new Enemy(cell, EnemyType.Skeleton, 10, 2);
                        }
                        case 'm' -> {
                            cell.setType(CellType.FLOOR);
                            new Enemy(cell, EnemyType.Mage, 10, 3);
                        }
                        case 'a' -> {
                            cell.setType(CellType.FLOOR);
                            new Mixture(cell, ItemType.LIFE_POTION, 5);
                        }
                        case 'b' -> {
                            cell.setType(CellType.FLOOR);
                            new Equipment(cell, ItemType.SWORD, 5);
                        }
                        case '@' -> {
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, 10, 5));
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
