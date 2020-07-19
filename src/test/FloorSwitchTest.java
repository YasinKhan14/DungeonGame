package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class FloorSwitchTest {

    @Test
    public void nothingOnSwitch(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        FloorSwitch floorSwitch = new FloorSwitch(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(floorSwitch);
        assertEquals(floorSwitch.getX(), 1);
        assertEquals(floorSwitch.getY(), 0);
        assertFalse(floorSwitch.isDestroyed());
    }

    @Test
    public void playerOnSwitch(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        FloorSwitch floorSwitch = new FloorSwitch(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(floorSwitch);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertEquals(floorSwitch.getX(), 1);
        assertEquals(floorSwitch.getY(), 0);
        assertTrue(floorSwitch.isDestroyed());
    }
    
    @Test
    public void playerPushBoulderOnSwitch(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        FloorSwitch floorSwitch = new FloorSwitch(2,0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(floorSwitch);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 0);
        assertEquals(floorSwitch.getX(), 2);
        assertEquals(floorSwitch.getY(), 0);
        assertTrue(floorSwitch.isDestroyed());
    }

}