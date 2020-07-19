package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;


public class DoorTest {
    
    @Test
    public void playerNoKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Door door = new Door(1,0,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(door);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(door.getX(), 1);
        assertEquals(door.getY(), 0);
        assertFalse(door.isDestroyed());

    }

    @Test
    public void playerWithCorrectKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(1,0, 0);
        Door door = new Door(2,0, 0);
        dungeon.setPlayer(player);
        dungeon.addEntity(key);
        dungeon.addEntity(door);
        player.moveRight();
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 0);
        assertTrue(door.isDestroyed());
    }

    @Test
    public void playerWithIncorrectKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Key key = new Key(1,0, 1);
        Door door = new Door(2,0,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(key);
        dungeon.addEntity(door);
        player.moveRight();
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertFalse(door.isDestroyed());
    }

    @Test
    public void playerPushBoulderWithKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(2, 0, dungeon);
        Key key = new Key(1,0,0);
        Door door = new Door(3,0,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(key);
        dungeon.addEntity(door);
        dungeon.addEntity(boulder);
        player.moveRight();
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 0);
        assertEquals(door.getX(), 3);
        assertEquals(door.getY(), 0);
        assertFalse(door.isDestroyed());
    }

    @Test
    public void playerPushBoulderWithoutKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Door door = new Door(2,0,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(door);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
        assertEquals(door.getX(), 2);
        assertEquals(door.getY(), 0);
        assertFalse(door.isDestroyed());
    }

    @Test
    public void enemyDoor(){
        Dungeon dungeon = new Dungeon(10, 10);
        Enemy enemy = new Enemy(1, 0, new GreedyEuclidean(), dungeon);
        Door door = new Door(2,0,0);
        dungeon.addEntity(enemy);
        dungeon.addEntity(door);
        enemy.moveRight();
        assertEquals(enemy.getX(), 1);
        assertEquals(enemy.getY(), 0);
        assertFalse(door.isDestroyed());
    }


}