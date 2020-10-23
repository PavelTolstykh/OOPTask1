package ru.vsu.cs.course1.task6;

public class Domino {
    public static void process() {
        int k = 7;
        GamePlayDomino gameplay = new GamePlayDomino(Making.makeTiles());
        gameplay.distribution(k);
        boolean isTiles1First = !gameplay.firstStep();
        gameplay.process(isTiles1First);
    }
}
