package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;



public class GhostEnemy extends Enemy {
    

    public GhostEnemy(int x, int y, MoveStrategy strategy, Dungeon dungeon, long tickRate) {
		super(x, y, strategy, dungeon, tickRate);
    }
    
    @Override
    public boolean canMove(int x, int y) {
		if (x < 0 || x >= getDungeon().getWidth() || y < 0 || y >= getDungeon().getHeight())
			return false;
		List<Entity> objectList = getDungeon().getMap()[y][x];
		List<Entity> copy = new ArrayList<Entity>();
        copy.addAll(objectList);
        for (Entity obj : copy){
			if(obj instanceof Player)
				return obj.allowPass(this);
        }
		return true;
    }
}