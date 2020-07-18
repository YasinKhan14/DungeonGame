package unsw.dungeon;

public class Key extends Entity{

    private boolean onMap;
    private int id;

    public Key(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			((Player) moveable).addKey(this);
			onMap = false;
		}
		return true;
	}

	public boolean defeatedObject() {
		return false;
	}

	public int getId() {
		return id;
	}
    
}