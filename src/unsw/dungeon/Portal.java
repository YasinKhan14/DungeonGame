package unsw.dungeon;

public class Portal extends Entity implements Interactable{

    private int id;

	public Portal(int x, int y) {
		super(x, y);
	}

	public int playerIntersect(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals involving portals
		return false;
	}
    
}