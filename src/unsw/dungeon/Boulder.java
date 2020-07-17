package unsw.dungeon;

public class Boulder extends Entity implements Interactable, Moveable{

	/**
	 * @param x
	 * @param y
	 */
	public Boulder(int x, int y) {
		super(x, y);
	}

	public int moveableIntersect(Moveable moveable) {
		// TODO Auto-generated method stub
		// should check dungeon map
		return -1; //-1 special value to denote its from boulder
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals invovling boulders;
		return false;
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