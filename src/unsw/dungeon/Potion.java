package unsw.dungeon;

public class Potion extends Entity {

    private boolean onMap;

    public Potion(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			onMap = false;
			((Player) moveable).playerRemove(this);
			((Player) moveable).playerGotPotion();
		}
		return true;
	}

	public boolean isDestroyed() {
		//stubbed for now as no goals invovling potion
		return false;
	}

}