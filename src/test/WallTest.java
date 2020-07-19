package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class WallTest {
    @Test
    public void interactTest(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        dungeon.setPlayer(player);
        Wall wall = new Wall(0, 1);
        dungeon.addEntity(wall);
        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertFalse(wall.isDestroyed());
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
}