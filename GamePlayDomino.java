package ru.vsu.cs.course1.task6;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GamePlayDomino {
    private List<Tile> tiles;
    private List<Tile> tiles1 = new LinkedList<>();
    private List<Tile> tiles2 = new LinkedList<>();
    private List<Tile> snake = new LinkedList<>();
    private int size = 28;

    public GamePlayDomino(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getSnake() {
        return snake;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getTiles1() {
        return tiles1;
    }

    public void setTiles1(List<Tile> tiles1) {
        this.tiles1 = tiles1;
    }

    public List<Tile> getTiles2() {
        return tiles2;
    }

    public void setTiles2(List<Tile> tiles2) {
        this.tiles2 = tiles2;
    }


    public void distribution(int k) {
        for (int j = 0; j < k; j++) {
            tiles1.add(tiles.remove((int)Math.floor(Math.random() * size)));
            size--;
            tiles2.add(tiles.remove((int)Math.floor(Math.random() * size)));
            size--;
        }
        System.out.println("Ваша колода: " + tiles1);
    }

    private void showFirstStep(List<Tile> playingTiles, Tile t) {
        playingTiles.remove(t);
        snake.add(t);
        System.out.println(snake);

    }

    public boolean firstStep() {
        for(int i = 1; i < 7; i++) {
            for (Tile t1 : tiles1) {
                if(t1.getLeftSide() == i && t1.getRightSide() == i) {
                    showFirstStep(tiles1, t1);
                    return true;
                }
            }
            for (Tile t2 : tiles2) {
                if (t2.getLeftSide() == i && t2.getRightSide() == i) {
                    showFirstStep(tiles2, t2);
                    return false;
                }
            }
        }
        System.out.println("Ваша колода: " + tiles1);
        return true;
    }

    public void process(boolean isTiles1First) {
        if(isTiles1First) {
            Scanner scanner = new Scanner(System.in);
            if (!step(tiles1.get(scanner.nextInt() - 1), tiles1)) {
                goMarket(tiles1);
            }
        } else {
            boolean isGoodStep = false;
            for (Tile t : tiles2) {
                if (step(t, tiles2)) {
                    isGoodStep = true;
                    break;
                }
            }
            if (!isGoodStep) {
                goMarket(tiles2);
            }
        }
        System.out.println(snake);
        System.out.println("Ваша колода: " + tiles1);
        int sum1 = 0;
        for (Tile t1 : tiles1) {
            sum1 += t1.getLeftSide() + t1.getRightSide();
        }
        int sum2 = 0;
        for (Tile t2 : tiles2) {
            sum2 += t2.getLeftSide() + t2.getRightSide();
        }
        if (tiles1.isEmpty() || (tiles.isEmpty() && sum1 < sum2)) {
            System.out.println("Вы выиграли");
        } else if (tiles2.isEmpty() || (tiles.isEmpty() && sum1 > sum2)) {
            System.out.println("Вы проиграли");
        } else {
            process(!isTiles1First);
        }
    }

    private boolean step(Tile tile, List<Tile> playingTiles) {
        if (snake.get(0).getLeftSide() == tile.getLeftSide()) {
            move(playingTiles, tile, true, 0);
            return true;
        } else if (snake.get(0).getLeftSide() == tile.getRightSide()) {
            move(playingTiles, tile, false, 0);
            return true;
        } else if (snake.get(snake.size() - 1).getRightSide() == tile.getLeftSide()) {
            move(playingTiles, tile, false, snake.size());
            return true;
        } else if (snake.get(snake.size() - 1).getRightSide() == tile.getRightSide()) {
            move(playingTiles, tile, true, snake.size());
            return true;
        }
        return false;
    }

    private void move(List<Tile> playingTiles, Tile tile, boolean isSwap, int index) {
        playingTiles.remove(tile);
        if (isSwap) {
            tile.swap();
        }
        snake.add(index, tile);
    }

    private void goMarket(List<Tile> playingTiles) {
        while (!tiles.isEmpty()) {
            Tile tile = tiles.remove((int)Math.floor(Math.random() * tiles.size()));
            if (!step(tile, playingTiles)) {
                playingTiles.add(tile);
            } else {
                break;
            }
        }
    }
}
