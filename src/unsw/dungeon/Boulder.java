package unsw.dungeon;

public class Boulder extends Entity implements Interactable{

	/**
	 * @param x
	 * @param y
	 */
	public Boulder(int x, int y) {
		super(x, y);
	}

	public int playerIntersect(Player player) {
		// TODO Auto-generated method stub
		return -1; //-1 special value to denote its from boulder
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals invovling boulders;
		return false;
	}
    
}