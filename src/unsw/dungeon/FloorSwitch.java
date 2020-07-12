package unsw.dungeon;

public class FloorSwitch extends Entity implements Interactable{

	private boolean triggered;

    public FloorSwitch(int x, int y) {
		super(x, y);
		triggered = false;
    }
    
	public int playerIntersect(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean defeatedObject() {
		return triggered;
	}
    
}