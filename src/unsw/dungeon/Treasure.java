package unsw.dungeon;

public class Treasure extends Entity {

    private boolean onMap;

	public Treasure(int x, int y) {
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
		return !onMap;
	}


    
}