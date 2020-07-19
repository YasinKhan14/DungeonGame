package unsw.dungeon;

public class FloorSwitch extends Entity{

	private boolean triggered;

    public FloorSwitch(int x, int y) {
		super(x, y);
		triggered = false;
    }
    
	public boolean allowPass(Moveable moveable) {
		triggered = true;
		return true;
	}

	public boolean isDestroyed() {
		return triggered;
	}
    
}