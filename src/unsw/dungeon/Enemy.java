package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class Enemy extends Entity implements Moveable, PlayerListener {

	private Dungeon dungeon;
	private MoveStrategy defaultStrategy;
	private MoveStrategy currentStrategy;
	private Timer moveTimer;
	private TimerTask moveTask;
	private boolean bothPlayerhavePotion;
	private long tickRate;


    public Enemy(int x, int y, MoveStrategy strategy, Dungeon dungeon, long tickRate) {
		super(x, y);
		this.defaultStrategy = strategy;
		this.currentStrategy = strategy;
		this.moveTimer = new Timer();
		this.moveTask = null;
		this.tickRate = tickRate;
		this.dungeon = dungeon;
		this.bothPlayerhavePotion = false;
		
	}
	
	public void nextMove(Player player, Player player2) {
		if (!(this.isOnMap())){
			moveTask.cancel();
			return;
		}
		else if (player.isDestroyed()) {
			if (player2 != null) {
				if (player2.isDestroyed()) {
					moveTask.cancel();
					return;
				}
			}
			else {
				moveTask.cancel();
				return;
			}
		}
		if (player != null || player2 != null)
			currentStrategy.nextMove(player, player2, this);
	}
	public void setPlayer(Player player, Player player2){
		if (player2 != null) 
			player2.addListener(this);
		player.addListener(this);
	}

	public void startMoving(Player player, Player player2){
		moveTask = new TimerTask(){
			@Override
			public void run(){
				Platform.runLater(new Runnable(){
					@Override
					public void run(){
						nextMove(player, player2);
					}
				});
			}
		};
		moveTimer.scheduleAtFixedRate(moveTask, tickRate, tickRate);
	}

	public void stopMoving(){
		if (moveTask == null)
			return;
		moveTask.cancel();
	}
	@Override
	public boolean allowPass(Moveable moveable) {

		if (moveable instanceof Player) {
			Player player = (Player) moveable;
			if (player.playerHasPotion()){
				player.playerRemove(this);
				this.setOffMap();
			}
			else if (player.hasSword()) {
				player.weaponDecrement();
				player.playerRemove(this);
				this.setOffMap();
			}
			else {
				player.defeated();
			}
		}
		return false;
	}

	@Override
	public void moveUp() {
		if (canMove(getX(), getY() -1))
			updateMap(getX(), getY() - 1);

	}

	@Override
	public void moveDown() {
		if (canMove(getX(), getY() + 1))
			updateMap(getX(), getY() + 1);
	}

	@Override
	public void moveLeft() {
		if (canMove(getX() - 1, getY()))
			updateMap(getX() - 1, getY());

	}

	@Override
	public void moveRight() {
		if (canMove(getX() + 1, getY()))
			updateMap(getX() + 1, getY());

	}

	@Override
	public boolean canMove(int x, int y) {
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
		return true;
	}

	@Override
	public void updateMap(int x, int y) {
		dungeon.updateMap(this, x, y);
	}
	
	public MoveStrategy getStrategy() {
		return currentStrategy;
	}
	
	@Override
	public void playerGotPotion(Boolean hasPotion){
		if (currentStrategy instanceof EscapeStrategy) {
			if (hasPotion) {
				bothPlayerhavePotion = true;
			}
			else {
				if (bothPlayerhavePotion)
					bothPlayerhavePotion = false;
				else
					currentStrategy = defaultStrategy;
			}		
		}
		else {
			currentStrategy = new EscapeStrategy();
		}


	}

	public Dungeon getDungeon() {
        return dungeon;
    }

}