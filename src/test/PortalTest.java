package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
public class PortalTest {
    @Test
    public void teleport(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        dungeon.setPlayer(player);
        Portal portal1 = new Portal(5, 6, dungeon);
        Portal portal2 = new Portal(3, 3, dungeon);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        assertFalse(portal1.isDestroyed());
    }
}