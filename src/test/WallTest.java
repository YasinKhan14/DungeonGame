package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class WallTest {
    @Test
    public void interactTest(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Wall wall1 = new Wall(5, 6);
        Wall wall2 = new Wall(5, 4);
        Wall wall3 = new Wall(6, 5);
        Wall wall4 = new Wall(4, 5);

        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);
        dungeon.addEntity(wall3);
        dungeon.addEntity(wall4);
        player.moveDown();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 5);
        player.moveUp();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 5);
        player.moveLeft();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 5);
        player.moveRight();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 5);
        assertFalse(wall1.isDestroyed());
    }

    @Test
    public void MovementTest(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        dungeon.setPlayer(player);
        player.moveDown();
        assertEquals(5, player.getX());
        assertEquals(6, player.getY());
        player.moveUp();
        assertEquals(5, player.getX());
        assertEquals(5, player.getY());
        player.moveLeft();
        assertEquals(4, player.getX());
        assertEquals(5, player.getY());
        player.moveRight();
        assertEquals(5, player.getX());
        assertEquals(5, player.getY());
    }
    @Test
    public void runningOut(){
        Dungeon dungeon = new Dungeon(1, 1);
        Player player = new Player(dungeon, 0, 0);
        dungeon.setPlayer(player);
        player.moveDown();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        player.moveLeft();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        player.moveRight();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        player.moveUp();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

}