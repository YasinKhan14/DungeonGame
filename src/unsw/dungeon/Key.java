package unsw.dungeon;

public class Key extends Entity implements Interactable{

    private boolean onMap;
    private int id;

    public Key(int x, int y) {
		super(x, y);
		onMap = true;
    }
    
	public int moveableIntersect(Moveable moveable) {
		onMap = false;
		return 0;
	}

	public boolean defeatedObject() {
		return false;
	}

	public int getId() {
		return id;
	}
    
}