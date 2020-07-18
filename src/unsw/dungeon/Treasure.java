package unsw.dungeon;

public class Treasure extends Entity {

    private boolean onMap;

	public Treasure(int x, int y) {
		super(x, y);
		onMap = true;
	}

	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			onMap = false;
			((Player) moveable).playerRemove(this);
		}
		return true;
	}

	public boolean defeatedObject() {
		return !onMap;
	}


    
}