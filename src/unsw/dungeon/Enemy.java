package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity implements Moveable, PlayerListener {

	private boolean onMap;
	private Dungeon dungeon;
	private MoveStrategy defaultStrategy;
	private MoveStrategy currentStrategy;


    public Enemy(int x, int y, MoveStrategy strategy, Dungeon dungeon) {
		super(x, y);
		onMap = true;
		this.defaultStrategy = strategy;
		this.currentStrategy = strategy;
	}
	
	public void nextMove(Player player) {
		currentStrategy.nextMove(player, this);
	}
 
	public boolean allowPass(Moveable moveable) {

		if (moveable instanceof Player) {
			Player player = (Player) moveable;
			if (player.hasSword()) {
				player.weaponDecrement();
				player.playerRemove(this);
			}
			else {
				player.defeated();
			}
		}
		return false;
	}

	public boolean isDestroyed() {
		return !onMap;
	}

	@Override
	public void moveUp() {
		updateMap(getX(), getY() + 1);

	}

	@Override
	public void moveDown() {
		updateMap(getX(), getY() - 1);
	}

	@Override
	public void moveLeft() {
		updateMap(getX() - 1, getY());

	}

	@Override
	public void moveRight() {
		updateMap(getX() + 1, getY());

	}

	@Override
	public boolean canMove(int x, int y) {
		List<Entity> objectList = dungeon.getMap()[y][x];
        for (Entity obj : objectList){
            if (obj == null) {
                continue;
            }
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
	public void playerGotPotion(){
		currentStrategy = new GreedyEuclidean();
	}
	@Override
	public void playerLostPotion(){
		currentStrategy = defaultStrategy;
	}
}