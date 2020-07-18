package unsw.dungeon;

public class Key extends Entity implements Interactable{

    private boolean onMap;
    private int id;

    public Key(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int moveableIntersect(Moveable moveable) {
		if (moveable instanceof Player) {
			((Player) moveable).addKey(this);
			onMap = false;
			return -1;
		}
		else {
			return 0;
		}
	}

	public boolean defeatedObject() {
		return false;
	}

	public int getId() {
		return id;
	}
    
}