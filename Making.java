package ru.vsu.cs.course1.task6;

import java.util.LinkedList;
import java.util.List;

public class Making {
    public static List<Tile> makeTiles() {
        List<Tile> tiles = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            tiles.add(new Tile(i, i));
            for (int j = 0; j < 7 && j != i; j++) {
                tiles.add(new Tile(i, j));
            }
        }
        return tiles;
    }
}
