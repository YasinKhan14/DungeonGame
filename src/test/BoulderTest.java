package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
//boulder, floorswitch, key, door, treasure, sword, goal

import unsw.dungeon.*;

public class BoulderTest {
    
    @Test
    public void singeBoulder(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
    }

    @Test
    public void doubleBoulder(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        Boulder boulder2 = new Boulder(2,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 0);
    }

    @Test
    public void boulderWall(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        Boulder wall = new Wall(2,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(wall);
        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
        assertEquals(wall.getX(), 2);
        assertEquals(wall.getY(), 0);
    }


    @Test
    public void boulderWallNotAdjacent(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(0,1, dungeon);
        Boulder boulder2 = new Boulder(0,2, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 1);
        assertEquals(boulder.getY(), 0);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 0);
    }
}
