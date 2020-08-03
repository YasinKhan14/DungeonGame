package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;


public class GhostEnemy extends Enemy {
    

    public GhostEnemy(int x, int y, MoveStrategy strategy, Dungeon dungeon) {
		super(x, y, strategy, dungeon);
    }
    
    @Override
    public boolean canMove(int x, int y) {
        return true;
    }
}