package unsw.dungeon;

public class Key extends Entity{

    private boolean onMap;
    private int id;

    public Key(int x, int y, int id) {
		super(x, y);
		onMap = true;
		this.id = id;
    }
    
	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			((Player) moveable).addKey(this);
			onMap = false;
		}
		return true;
	}

	public boolean isDestroyed() {
		return !onMap;
	}

	public int getId() {
		return id;
	}
    
}