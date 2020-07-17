package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity implements Interactable {

	Dungeon dungeon;
	/**
	 * @param x
	 * @param y
	 */
	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public int playerIntersect(Player player) {
		int dx, dy;
		dx = getX() - player.getX();
		dy = getY() - player.getY();
		if (dx == 0){
			if (dy == -1 && canMove(getX(), getY() - 1 )) {
				moveUp();
				return 0;
			} else if (dy == 1 && canMove(getX(), getY() + 1)){
				moveDown();
				return 0;
			}
		} else if (dy == 0){
			if (dx == -1 && canMove(getX() - 1, getY())){
				moveLeft();
				return 0;
			}else if (dx == 1 && canMove(getX() + 1, getY())){
				moveRight();
				return 0;
			}
		}
		return 1; //1 special value to denote its from boulder
	}

	public boolean defeatedObject() {
		//stubbed for now as no goals invovling boulders;
		return false;
	}
	
	public boolean canMove(int x, int y){
		List<Entity> objectList = dungeon.getMap()[y][x];
		for (Entity obj : objectList){
			if (obj == null) {
				continue;
			}
			if (obj instanceof Wall || obj instanceof Boulder){
				return false;
			}
		}
		return true;
	}

	public void moveUp() {
        if (getY() > 0) {
            updateMap(getX(), getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            updateMap(getX(), getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            updateMap(getX() - 1, getY());
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            updateMap(getX() + 1, getY());
        }
	}
	public void updateMap(int x, int y){
        dungeon.updateMap(this, x, y);
    }
}