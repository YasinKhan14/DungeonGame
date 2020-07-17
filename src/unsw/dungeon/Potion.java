package unsw.dungeon;

public class Potion extends Entity implements Interactable {

    private boolean onMap;

    public Potion(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int moveableIntersect(Player player) {
		onMap = false;
		return 0;
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals invovling potion
		return false;
	}

}