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
        Wall wall = new Wall(0, 1);
        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        assertFalse(wall.isDestroyed());
    }
}