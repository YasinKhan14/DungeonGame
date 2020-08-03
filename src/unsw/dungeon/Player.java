package unsw.dungeon;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


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
    private BooleanProperty goalComplete;
    private BooleanProperty defeated;
    private LocalDateTime potionTriggerTime;
    private long remaining;
    private IntegerProperty swordCount;
    private IntegerProperty potionTick;
    private IntegerProperty treasureCount;
    private IntegerProperty keyCount;
    
    private class PotionTask extends TimerTask{
        @Override
        public void run(){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    potionTick.set(potionTick.get() - 1);
                    if (potionTick.get() == 0){
                        notifyPlayerGotPotion(false);
                        hasPotion = false;
                    } else {
                        currentTask.cancel();
                        potionTimer.purge();
                        potionTimer.schedule(new PotionTask(), 1000);
                    }
                }
            });
        };
        
    };
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
        this.weapon = null;
        this.goal = null;
        this.potionTriggerTime = null;
        this.currentTask = null;
        this.remaining = -1;
        goalComplete = new SimpleBooleanProperty(false);
        defeated = new SimpleBooleanProperty(false);
        swordCount = new SimpleIntegerProperty(0);
        potionTick = new SimpleIntegerProperty(0);
        treasureCount = new SimpleIntegerProperty(0);
        keyCount = new SimpleIntegerProperty(0);

    }
    public IntegerProperty getKeyCount(){
        return keyCount;
    }
    public void pickTreasure(){
        treasureCount.set(treasureCount.get() + 100);
    }
    public IntegerProperty getScore(){
        return treasureCount;
    }
    public void equipSword(Weapon weapon){
        this.weapon = weapon;
        swordCount.set(5);
    }
    public boolean hasSword(){
        if (weapon == null){
            return false;
        }
        return true;
    }
    public void weaponDecrement(){
        weapon.swing();
        swordCount.set(swordCount.get() - 1);
        if (weapon.getCharges() == 0) {
            weapon = null;
        }
    }
    public IntegerProperty getSwordCount(){
        return swordCount;
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
        keyCount.set(keyCount.get() + 1);
    }

    public List<Key> getKeys(){
        return keys;
    }
    public void addListener(PlayerListener listener){
        listeners.add(listener);
    }
    public void playerGotPotion(){
        if (hasPotion && currentTask != null){
            currentTask.cancel();
        }else{
            hasPotion = true;
            notifyPlayerGotPotion(true);
        }
        potionTick.set(5);
        currentTask = new PotionTask();
        potionTimer.schedule(currentTask, 1000);
        potionTriggerTime = LocalDateTime.now();
        hasPotion = true;
    }


    public void pausePotionTimer() {
        if (potionTriggerTime == null || currentTask == null) {
            remaining = -1;
            return;
        }
        currentTask.cancel();
        potionTimer.purge();
        remaining = 1000 - Duration.between(potionTriggerTime, LocalDateTime.now()).toMillis();
    }

    public void resumePotionTimer() {
        if (remaining < 0)
            return;
        currentTask = new PotionTask();
        potionTimer.schedule(currentTask, remaining);

    }


    public void notifyPlayerGotPotion(Boolean hasPotion){
        for (PlayerListener listener : listeners){
            listener.playerGotPotion(hasPotion);
        }
    }

    public boolean playerHasPotion() {
        return hasPotion;
    }
    public IntegerProperty getPotionTick(){
        return potionTick;
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
            if(obj.allowPass(this))
                continue;
            else
                return false;
        }

        if(isCompleted()){
            goalComplete.set(true);
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
            if (enemy.isDestroyed())
                return true;
            if (hasPotion){
                playerRemove(enemy);
                enemy.setOffMap();;
                return true;
            }
            else if (hasSword()){
                weaponDecrement();
                playerRemove(enemy);
                enemy.setOffMap();
                return true;
            }
            else {
                defeated();
                return true;
            }
        }
        return false;
    }

    public void defeated() {
        this.setOffMap();
        playerRemove(this);
        defeated.set(true);
    }

    public BooleanProperty getDefeated(){
        return defeated;
    }

    public BooleanProperty goalComplete(){
        return goalComplete;
    }

}