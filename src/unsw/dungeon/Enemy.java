package unsw.dungeon;

public class Enemy extends Entity implements Interactable, Moveable {

	private boolean onMap;

    public Enemy(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int moveableIntersect(Moveable moveable) {
		//check if player as sword. by pulling player inventory
		//if player has sword then -> onMap = false;
		return 0;
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
		// TODO Auto-generated method stub

	}
    
}