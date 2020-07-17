package unsw.dungeon;

import java.util.List;

public class Door extends Entity implements Interactable{

    private int id;
    private boolean isOpen;

    public Door(int x, int y) {
      super(x, y);
    }

	public int moveableIntersect(Moveable moveable) {
        //check keys player.getKeys() -> return list
        // check if list contains id, i.e player has a key for matching door (loop through list)
			//if conditions satisfied then isOpen = true
		if (moveable instanceof Player) {
			Player player = (Player)moveable;
			List<Key> keys = player.getKeys();
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