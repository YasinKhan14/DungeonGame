package unsw.dungeon;

public class Key extends Entity implements Interactable{

    private boolean onMap;
    private int id;

    public Key(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int playerIntersect(Player player) {
		onMap = false;
		return 0;
	}

	public boolean defeatedObject() {
		return false;
	}
    
}