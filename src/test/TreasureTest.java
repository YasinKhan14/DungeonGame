package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class TreasureTest {

    @Test
    public void treasureRemoved(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(treasure);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
        assertTrue(treasure.isDestroyed());
    }
}