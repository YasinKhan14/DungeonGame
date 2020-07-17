package unsw.dungeon;

public class Wall extends Entity implements Interactable{

    public Wall(int x, int y) {
        super(x, y);
    }

	public int moveableIntersect(Moveable moveable) {
		// TODO Auto-generated method stub
		return 1;
	}

	public boolean defeatedObject() {
		//again no goals invovle wall
		return false;
	}




}
