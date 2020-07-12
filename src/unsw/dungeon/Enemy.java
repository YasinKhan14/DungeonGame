package unsw.dungeon;

public class Enemy extends Entity implements Interactable {

	private boolean onMap;

    public Enemy(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int playerIntersect(Player player) {
		//check if player as sword. by pulling player inventory
		//if player has sword then -> onMap = false;
		return 0;
	}

	public boolean defeatedObject() {
		return !onMap;
	}
    
}