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
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.STONEWALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'c':
                            cell.setType(CellType.CLOSEDDOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Enemy(cell, EnemyType.Skeleton, 10, 2);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Enemy(cell, EnemyType.Mage, 10, 3);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Mixture(cell, ItemType.LIFE_POTION, 5);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Equipment(cell, ItemType.SWORD, 5);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, 10,5));
                            break;
                        case 'd':
                            cell.setType(CellType.OPENDOOR);
                            break;
                        case '^':
                            cell.setType(CellType.UPSTAIRS);
                            break;
                        case 'v':
                            cell.setType(CellType.DOWNSTAIRS);
                            break;
                        case '%':
                            cell.setType(CellType.MAPLEWALL);
                            break;
                        case 'w':
                            cell.setType(CellType.MAPLEWINDOW);
                            break;
                        case 't':
                            cell.setType(CellType.TORCH);
                            break;
                        case '+':
                            cell.setType(CellType.STONEFLOOR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
