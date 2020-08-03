package unsw.dungeon;

public class Treasure extends Entity {


	public Treasure(int x, int y) {
		super(x, y);
	}

	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Player) {
			this.setOffMap();
			((Player) moveable).playerRemove(this);
			((Player) moveable).pickTreasure();
		}
		return true;
	}

}