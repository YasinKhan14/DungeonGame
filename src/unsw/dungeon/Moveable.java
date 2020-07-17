package unsw.dungeon;

public interface Moveable {

    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();

    public boolean canMove(int x, int y);
    public void updateMap(int x, int y);

}