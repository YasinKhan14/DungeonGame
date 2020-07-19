package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity implements Moveable {

	Dungeon dungeon;
	/**
	 * @param x
	 * @param y
	 */
	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
	}
	@Override
	public boolean allowPass(Moveable moveable) {
		if (!(moveable instanceof Player))
			return false;
		Player player = (Player) moveable;
		int dx, dy;
		dx = getX() - player.getX();
		dy = getY() - player.getY();
		if (dx == 0){
			if (dy == -1 && canMove(getX(), getY() - 1 )) {
				moveUp();
				return true;
			} else if (dy == 1 && canMove(getX(), getY() + 1)){
				moveDown();
				return true;
			}
		} else if (dy == 0){
			if (dx == -1 && canMove(getX() - 1, getY())){
				moveLeft();
				return true;
			}else if (dx == 1 && canMove(getX() + 1, getY())){
				moveRight();
				return true;
			}
		}
		return false; 
	}

	@Override
	public boolean isDestroyed() {
		//stubbed for now as no goals invovling boulders;
		return false;
	}
	@Override
	public boolean canMove(int x, int y){
		List<Entity> objectList = dungeon.getMap()[y][x];
		for (Entity obj : objectList){
			if (obj == null) {
				continue;
			}
			if (!obj.allowPass(this)){
				return false;
			}
		}
		return true;
	}

	@Override
	public void moveUp() {
        if (getY() > 0) {
            updateMap(getX(), getY() - 1);
        }
    }
	@Override
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            updateMap(getX(), getY() + 1);
        }
    }
	@Override
    public void moveLeft() {
        if (getX() > 0) {
            updateMap(getX() - 1, getY());
        }
    }
	@Override
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            updateMap(getX() + 1, getY());
        }
	}
	@Override
	public void updateMap(int x, int y){
        dungeon.updateMap(this, x, y);
    }
}