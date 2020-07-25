package unsw.dungeon;

public class Potion extends Entity {

    private boolean onMap;

    public Potion(int x, int y) {
		super(x, y);
    }
    @Override
	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			this.setOffMap();
			((Player) moveable).playerRemove(this);
			((Player) moveable).playerGotPotion();
		}
		return true;
	}


}