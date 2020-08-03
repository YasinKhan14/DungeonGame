package unsw.dungeon;

public class Node {

    private int x;
    private int y;
    private int g;
    private int h;
    private int f;
    private Node prev;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = -1;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getF() {
        return f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getG() {
        return g;
    }

    public void setPrev(Node node) {
        this.prev = node;
    }

    public Node getPrev() {
        return prev;
    }


    

}