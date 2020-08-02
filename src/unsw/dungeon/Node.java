package unsw.dungeon;

public class Node {

    private int x;
    private int y;
    private double h;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setH(double h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getH() {
        return h;
    }


}