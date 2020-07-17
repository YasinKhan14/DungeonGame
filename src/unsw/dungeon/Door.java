package unsw.dungeon;

public class Door extends Entity implements Interactable{

    private int id;
    private boolean isOpen;

    public Door(int x, int y) {
      super(x, y);
    }

	public int moveableIntersect(Player player) {
        //check keys player.getKeys() -> return list
        // check if list contains id, i.e player has a key for matching door (loop through list)
            //if conditions satisfied then isOpen = true
		  return 0;
	}

	public boolean defeatedObject() {
		return isOpen;
	}
    
}