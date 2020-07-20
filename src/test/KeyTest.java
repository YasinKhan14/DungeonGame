package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class KeyTest {
    
    @Test
    public void keyPickup(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(1,0,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(key);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertTrue(player.getKeys().contains(key));
        assertTrue(key.isDestroyed());
    }

    @Test
    public void notPlayerKeyPickup(){
        Dungeon dungeon = new Dungeon(10, 10);
        Enemy enemy = new Enemy(0, 0, new GreedyEuclidean(), dungeon);
        Key key = new Key(1,0,0);
        dungeon.addEntity(key);
        dungeon.addEntity(enemy);
        enemy.moveRight();
        assertFalse(key.isDestroyed());
    }
}