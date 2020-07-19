package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class EnemyTest {
    @Test
    public void easyWalk() {
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        dungeon.setPlayer(player);
        enemy.setPlayer(player);
        dungeon.addEntity(enemy);
        assertFalse(player.isDestroyed());
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        enemy.startMoving();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(player.isDestroyed());

    }
    @Test
    public void bigWall(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        dungeon.setPlayer(player);
        enemy.setPlayer(player);
        dungeon.addEntity(enemy);

        for (int i = 9; i > 0; i --){
            Wall wall = new Wall(5, i);
            dungeon.addEntity(wall);
        }
        enemy.startMoving();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(player.isDestroyed());
    }
}