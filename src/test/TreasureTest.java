package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class TreasureTest {

    @Test
    public void treasureRemoved(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(treasure);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertTrue(treasure.isDestroyed());
    }

    @Test
    public void enemyTreasure(){
        Dungeon dungeon = new Dungeon(10, 10);
        Enemy enemy = new Enemy(0, 0, new GreedyEuclidean(), dungeon, 500);
        Treasure treasure = new Treasure(1,0);
        dungeon.addEntity(treasure);
        dungeon.addEntity(enemy);
        enemy.moveRight();
        assertFalse(treasure.isDestroyed());
    }
}