package ru.vsu.cs.course1.task6;

public class Tile {
    private int leftSide, rightSide;

    public int getLeftSide() {
        return leftSide;
    }

    public int getRightSide() {
        return rightSide;
    }

    public Tile(int leftSide, int rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public void swap() {
        int tmp = leftSide;
        leftSide = rightSide;
        rightSide = tmp;
    }

    @Override
    public String toString() {
        return "{" + leftSide + " | " + rightSide + '}';
    }
}
