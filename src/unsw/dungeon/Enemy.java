package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity implements Moveable {

	private boolean onMap;
	private Dungeon dungeon;
	private MoveStrategy strategy;


    public Enemy(int x, int y, MoveStrategy strategy) {
		super(x, y);
		onMap = true;
		this.strategy = strategy;
	}
	
	public void nextMove(Player player) {
		strategy.nextMove(player, this);
	}

    
	public boolean allowPass(Moveable moveable) {
		if (((Player) moveable).hasSword() > 0 ) {
			((Player) moveable).playerRemove(this);
		}
		else {
			((Player) moveable).defeated();
		}
		return true;
	}

	public boolean defeatedObject() {
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
    
}