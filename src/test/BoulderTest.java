package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//boulder, floorswitch, key, door, treasure, sword, goal

import unsw.dungeon.*;

public class BoulderTest {
    
    @Test
    public void singeBoulderRight(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 0);
    }

    @Test
    public void singeBoulderLeft(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 2, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveLeft();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertEquals(boulder.getX(), 0);
        assertEquals(boulder.getY(), 0);
    }

    @Test
    public void singeBoulderDown(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(0,1, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(boulder.getX(), 0);
        assertEquals(boulder.getY(), 2);
    }

    @Test
    public void singeBoulderUp(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 2);
        Boulder boulder = new Boulder(0,1, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        player.moveUp();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(boulder.getX(), 0);
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
        assertEquals(boulder2.getX(), 2);
        assertEquals(boulder2.getY(), 0);
    }

    @Test
    public void boulderWall(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1,0, dungeon);
        Wall wall = new Wall(2,0);
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
    public void doubleBoulderNotAdjacent(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(0,1, dungeon);
        Boulder boulder2 = new Boulder(1,1, dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(boulder.getX(), 0);
        assertEquals(boulder.getY(), 2);
        assertEquals(boulder2.getX(), 1);
        assertEquals(boulder2.getY(), 1);
    }
}
