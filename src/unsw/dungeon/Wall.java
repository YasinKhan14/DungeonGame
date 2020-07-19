package unsw.dungeon;

public class Wall extends Entity{

    public Wall(int x, int y) {
        super(x, y);
    }

	public boolean allowPass(Moveable moveable) {
		return false;
	}

	public boolean isDestroyed() {
		return false;
	}




}
