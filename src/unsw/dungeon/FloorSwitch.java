package unsw.dungeon;

public class FloorSwitch extends Entity{

	private Boulder boulder;

    public FloorSwitch(int x, int y) {
		super(x, y);
		boulder = null;
    }
    
	public boolean allowPass(Moveable moveable) {
		if (moveable instanceof Boulder) {
			boulder = (Boulder) moveable;
		}
		return true;
	}

	@Override
	public boolean isDestroyed() { //if switch is triggered (can't be destoryed)
		if (boulder == null) {
			return false;
		}
		else { //check if most recent boulder object has same coords
			if (boulder.getX() == this.getX() && boulder.getY() == this.getY())
				return true;
			return false;
		}
	}
}