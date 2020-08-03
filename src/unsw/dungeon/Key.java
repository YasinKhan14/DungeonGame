package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key extends Entity{

	private int id;
	private BooleanProperty used;

    public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
		used = new SimpleBooleanProperty(false);
    }
    
	public boolean allowPass(Moveable moveable) {
		if (isDestroyed()){
			return true;
		}
		if (moveable instanceof Player) {
			((Player) moveable).addKey(this);
			this.setOffMap();
		}
		return true;
	}

	public int getId() {
		return id;
	}
	
	public void use(){
		used.set(true);
	}

	public BooleanProperty getUsage(){
		return used;
	}
}