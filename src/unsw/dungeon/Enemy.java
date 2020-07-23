package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Entity implements Moveable, PlayerListener {

	private Dungeon dungeon;
	private MoveStrategy defaultStrategy;
	private MoveStrategy currentStrategy;
	private Timer moveTimer;
	private TimerTask moveTask;
	private Player player;


    public Enemy(int x, int y, MoveStrategy strategy, Dungeon dungeon) {
		super(x, y);
		this.defaultStrategy = strategy;
		this.currentStrategy = strategy;
		this.moveTimer = new Timer();
		this.moveTask = null;
		this.dungeon = dungeon;
		
	}
	
	public void nextMove(Player player) {
		if (!(this.isOnMap()) || player.isDestroyed()){
			moveTask.cancel();
			return;
		}
		if (player != null)
			currentStrategy.nextMove(player, this);
	}
	public void setPlayer(Player player){
		this.player = player;
		player.addListener(this);
	}
	public void startMoving(){
		moveTask = new TimerTask(){
			public void run(){
				nextMove(player);
				System.out.println("x = " + getX() + " y = " + getY());
			}
		};
		moveTimer.scheduleAtFixedRate(moveTask, 500, 500);
	}
	@Override
	public boolean allowPass(Moveable moveable) {

		if (moveable instanceof Player) {
			Player player = (Player) moveable;
			if (player.hasSword()) {
				player.weaponDecrement();
				player.playerRemove(this);
				this.setOffMap();
			}
			else if (currentStrategy instanceof EscapeStrategy){
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
	public boolean isDestroyed() {
		return !(this.isOnMap());
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
		if (hasPotion)
			currentStrategy = new EscapeStrategy();
		else
			currentStrategy = defaultStrategy;

	}

}