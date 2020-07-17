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
    private int sword;
    //enemy listener?
    //weapon -> time active?
    //potion -> time active?
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
        this.sword = 0;
    }
  
    public void moveUp() {
        if (getY() > 0 && canMove(getX(), getY() -1)) {
            updateMap(getX(), getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && canMove(getX(), getY() + 1)) {
            updateMap(getX(), getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0 && canMove(getX() - 1, getY())) {
            updateMap(getX() - 1, getY());
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && canMove(getX() + 1, getY())) {
            updateMap(getX() + 1, getY());
        }
    }
    /**
     * Check if player is able to move to a coordinate on the map, if the player is 
     * able to, deal with interaction before moving in. Otherwise return false
     * @param x 
     * @param y
     * @return
     */
    public boolean canMove(int x, int y){
        List<Entity> objectList = dungeon.getMap()[y][x];
        for (Entity obj : objectList){
            if (obj == null) {
                continue;
            }
            // assuming wall is also interactable
            if (!(obj instanceof Interactable)){
                continue;
            }
            // type casting into interactables
            Interactable i = (Interactable) obj;

            switch(i.moveableIntersect(this)){
                // wall
                case 1:
                    return false;
                // interactables
                case 0:
                    break;
                case -1:
                    dungeon.removeFromMap(obj);
                    break;

            }
            continue;
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

    public void updateMap(int x, int y){
        dungeon.updateMap(this, x, y);
    }
    public void equipSword(){
        sword = 5;
    }
    public boolean hasSword(){
        if (sword == 0){
            return false;
        }
        return true;
    }
    @Override
    public boolean isCompleted(){
        if (goal.isCompleted())
            return true;
        else
            return false;
    }
}
