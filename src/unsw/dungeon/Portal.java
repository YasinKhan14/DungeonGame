package unsw.dungeon;

import java.util.List;

public class Portal extends Entity {

	private int id;
	private Dungeon dungeon;

	public Portal(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public boolean allowPass(Moveable moveable) {

		List<Entity> entityList = dungeon.getEntities();
		for (Entity entity : entityList) {
			if (entity instanceof Portal) {
				Portal portal = (Portal) entity;
				if (this != portal && portal.getId() == id) {
					moveable.updateMap(portal.getX(), portal.getY()); 
					return true;
				}
			}
		}
		return false;
	}

	public boolean isDestroyed() {
		//stubbed for now as no goals involving portals
		return false;
	}

	public int getId() {
		return id;
	}
    
}