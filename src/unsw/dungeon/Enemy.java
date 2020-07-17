package unsw.dungeon;

import javax.lang.model.util.ElementScanner6;

public class Enemy extends Entity implements Interactable, Moveable {

	private boolean onMap;
	private Dungeon dungeon;

    public Enemy(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int moveableIntersect(Moveable moveable) {
		if (((Player) moveable).hasSword() > 0 ) 
			return - 1;
		else
			return -2;
	}

	public boolean defeatedObject() {
		return !onMap;
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canMove(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateMap(int x, int y) {
		dungeon.updateMap(this, x, y);
	}
    
}