package unsw.dungeon;

public class Wall extends Entity{

    public Wall(int x, int y) {
        super(x, y);
    }

	public int allowPass(Moveable moveable) {
		// TODO Auto-generated method stub
		return 1;
	}

	public boolean defeatedObject() {
		//again no goals invovle wall
		return false;
	}




}
