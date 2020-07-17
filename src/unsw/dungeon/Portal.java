package unsw.dungeon;

import java.util.List;

public class Portal extends Entity implements Interactable {

	private int id;
	private Dungeon dungeon;

	public Portal(int x, int y) {
		super(x, y);
	}

	public int moveableIntersect(Moveable moveable) {

		// TODO Auto-generated method stub
		List<Entity> entityList = dungeon.getEntities();
		for (Entity entity : entityList) {
			if (entity instanceof Portal) {
				Portal portal = (Portal) entity;
				if (portal.getId() == id) {
					moveable.updateMap(portal.getX(), portal.getY()); 
					return 0;
				}
			}
		}
		
		return 0;
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals involving portals
		return false;
	}

	public int getId() {
		return id;
	}
    
}