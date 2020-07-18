package unsw.dungeon;

import java.util.List;

public class Door extends Entity{

    private int id;
    private boolean isOpen;

    public Door(int x, int y) {
      super(x, y);
    }

	public int allowPass(Moveable moveable) {
		
		if (moveable instanceof Player) {
			List<Key> keys = ((Player)moveable).getKeys();
			for (Key key : keys) {
				if (key.getId() == id) {
					return -1;
				}
			}
		}
		return 1;
	}

	public boolean defeatedObject() {
		return isOpen;
	}
    
}