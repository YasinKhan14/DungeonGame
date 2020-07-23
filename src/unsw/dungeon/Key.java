package unsw.dungeon;

public class Key extends Entity{

    private int id;

    public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
    }
    
	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			((Player) moveable).addKey(this);
			this.setOffMap();
		}
		return true;
	}

	public boolean isDestroyed() {
		return !(this.isOnMap());
	}

	public int getId() {
		return id;
	}
    
}