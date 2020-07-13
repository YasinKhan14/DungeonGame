package unsw.dungeon;

import java.util.*;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Goal{

    private Dungeon dungeon;
    private List<Key> keys;
    private Goal goal;
    //enemy listener?

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, Goal goal) {
        super(x, y);
        this.dungeon = dungeon;
        this.goal = goal;
        this.keys = new ArrayList<Key>();
    }
  
    public void moveUp() {
        if (getY() > 0 && canMove(getX(), getY() -1)) {
            dungeon.getMap()[getY()][getX()] = null;
            y().set(getY() - 1);
            dungeon.getMap()[getY()][getX()] = this;
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && canMove(getX(), getY() + 1)) {
            dungeon.getMap()[getY()][getX()] = null;
            y().set(getY() + 1);
            dungeon.getMap()[getY()][getX()] = this;
        }
    }

    public void moveLeft() {
        if (getX() > 0 && canMove(getX() - 1, getY())) {
            dungeon.getMap()[getY()][getX()] = null;
            x().set(getX() - 1);
            dungeon.getMap()[getY()][getX()] = this;
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && canMove(getX() + 1, getY())) {
            dungeon.getMap()[getY()][getX()] = null;
            x().set(getX() + 1);
            dungeon.getMap()[getY()][getX()] = this;
    }

    public boolean canMove(int x, int y){
        Entity obj = dungeon.getMap()[x][y];
        if (obj == null) {
            return true;
        }
        // assuming wall is also interactable
        if (!(obj instanceof Interactable)){
            return true;
        }
        // type casting into interactables
        Interactable i = (Interactable) obj;

        switch(i.playerIntersect(this)){
            // wall
            case 1:
                return false;
            // interactables
            case 0:
                dungeon.getMap()[y][x] = null;
                return true;

                
        }
        if(isCompleted()){
            // trigger end game function
        }

        return true;
    }
    public void addKey(Key key){
        this.keys.add(key);
    }

    public List<Key> getKeys(){
        return keys;
    }
    @Override
    public boolean isCompleted(){
        if (goal.isCompleted())
            return true;
        else
            return false;
    }
}
