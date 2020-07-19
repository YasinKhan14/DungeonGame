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
    public void noKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder door = new Door(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(door);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(door.getX(), 1);
        assertEquals(door.getY(), 0);

    }

    public void withKey(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder key = new Key(1,0);
        Boulder door = new Door(2,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(key);
        dungeon.addEntity(door);
        player.moveRight();
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 0);
        assertTrue(door.isDestroyed());
    }
}