package sk.stuba.fei.uim.oop.path;

public enum Directions {
    UP(0,-1),
    DOWN(0,1),
    RIGHT(1,0),
    LEFT(-1,0);

    private final int x;
    private final int y;

    Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
