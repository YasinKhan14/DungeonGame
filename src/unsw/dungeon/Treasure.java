package unsw.dungeon;

public class Treasure extends Entity implements Interactable {

    private boolean onMap;

	public Treasure(int x, int y) {
		super(x, y);
		onMap = true;
	}

	public int moveableIntersect(Moveable moveable) {
		onMap = false;
		return 0;
	}

	public boolean defeatedObject() {
		return !onMap;
	}


    
}