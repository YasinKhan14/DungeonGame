package unsw.dungeon;

public class Potion extends Entity {

    private boolean onMap;

    public Potion(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			onMap = false;
			return -1;
		}
		else {
			return 0;
		}
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals invovling potion
		return false;
	}

}