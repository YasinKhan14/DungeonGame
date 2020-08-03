package unsw.dungeon;

import java.util.List;

public class Door extends Entity{

    private int id;

    public Door(int x, int y, int id) {
	  super(x, y);
	  this.id = id;
    }

	public boolean allowPass(Moveable moveable) {
		if (isDestroyed()){
			return true;
		}
		if (moveable instanceof Player) {
			List<Key> keys = ((Player)moveable).getKeys();
			for (Key key : keys) {
				if (key.getId() == id) {
					this.setOffMap();
					key.use();
					return true;
				}
			}
		}
		return false;
	}

    
}