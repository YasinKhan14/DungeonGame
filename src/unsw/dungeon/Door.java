package unsw.dungeon;

import java.util.List;

public class Door extends Entity{

    private int id;
    private boolean isOpen;

    public Door(int x, int y, int id) {
	  super(x, y);
	  isOpen = false;
	  this.id = id;
    }

	public boolean allowPass(Moveable moveable) {
		
		if (moveable instanceof Player) {
			List<Key> keys = ((Player)moveable).getKeys();
			for (Key key : keys) {
				if (key.getId() == id) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isDestroyed() {
		return isOpen;
	}
    
}