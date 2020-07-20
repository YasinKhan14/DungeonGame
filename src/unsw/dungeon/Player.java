package unsw.dungeon;

import java.util.*;


/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Moveable{

    private Dungeon dungeon;
    private List<Key> keys;
    private Goal goal;
    private Weapon weapon;
    private List<PlayerListener> listeners;
    private boolean hasPotion;
    private Timer potionTimer;
    private TimerTask currentTask;
    private Boolean alive;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.keys = new ArrayList<Key>();
        this.listeners = new ArrayList<PlayerListener>();
        this.potionTimer = new Timer();
        this.hasPotion = false;
        this.alive = true;
        this.weapon = null;
        this.goal = null;

    }
    public void equipSword(Weapon weapon){
        this.weapon = weapon;
    }
    public boolean hasSword(){
        if (weapon == null){
            return false;
        }
        return true;
    }
    public void setGoal(Goal goal){
        this.goal = goal;
    }
    public boolean isCompleted(){
        if (goal == null)
            return false;
        return goal.isCompleted();
    }
    public void playerRemove(Entity entity){
        dungeon.removeFromMap(entity);
    }
    public void addKey(Key key){
        this.keys.add(key);
    }

    public List<Key> getKeys(){
        return keys;
    }

    public void weaponDecrement(){
        weapon.swing();
        if (weapon.getCharges() == 0) {
            weapon = null;
        }
    }
    public void addListener(PlayerListener listener){
        listeners.add(listener);
    }
    public void playerGotPotion(){
        if (hasPotion && currentTask != null){
            currentTask.cancel();
        }else{
            notifyPlayerGotPotion(true);
        }
        currentTask = new TimerTask(){
            public void run(){
                notifyPlayerGotPotion(false);
                hasPotion = false;
            }
        };
        potionTimer.schedule(currentTask, 5000);
        hasPotion = true;
    }
    public void notifyPlayerGotPotion(Boolean hasPotion){
        for (PlayerListener listener : listeners){
            listener.playerGotPotion(hasPotion);
        }
    }
	@Override
    public void moveUp() {
        if (canMove(getX(), getY() -1)) {
            updateMap(getX(), getY() - 1);
        }
    }
	@Override
    public void moveDown() {
        if (canMove(getX(), getY() + 1)) {
            updateMap(getX(), getY() + 1);
        }
    }
	@Override
    public void moveLeft() {
        if (canMove(getX() - 1, getY())) {
            updateMap(getX() - 1, getY());
        }
    }
	@Override
    public void moveRight() {
        if (canMove(getX() + 1, getY())) {
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
    @Override
    public boolean canMove(int x, int y){
        if (x < 0 || x >= dungeon.getWidth() || y < 0 || y >= dungeon.getHeight())
			return false;
        List<Entity> objectList = dungeon.getMap()[y][x];
        List<Entity> copy = new ArrayList<Entity>();
        copy.addAll(objectList);
        for (Entity obj : copy){
            if (obj == null) {
                continue;
            }
            if(obj.allowPass(this))
                continue;
            else
                return false;
        }

        if(isCompleted()){
            // trigger end game function
        }
        return true;
    }
	@Override
    public void updateMap(int x, int y){
        dungeon.updateMap(this, x, y);
    }
    @Override
    public boolean allowPass(Moveable moveable){
        if (moveable instanceof Enemy){
            Enemy enemy = (Enemy) moveable;
            if (hasSword()){
                weapon.isDestroyed();
                playerRemove(enemy);
                enemy.setOnMap(false);
                return true;
            }
            else if (enemy.getStrategy() instanceof EscapeStrategy){
                playerRemove(enemy);
                enemy.setOnMap(false);
                return true;
            }
            else {
                defeated();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDestroyed(){
        return !alive;
    }

    public void defeated() {
        alive = false;
    }
}