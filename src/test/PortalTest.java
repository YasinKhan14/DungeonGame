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
        Portal portal1 = new Portal(5, 6, dungeon, 1);
        Portal portal2 = new Portal(3, 3, dungeon, 1);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        assertFalse(portal1.isDestroyed());
    
    }
    @Test
    public void enemyTeleport(){
        Dungeon dungeon = new Dungeon(10, 10);
        Enemy enemy = new Enemy(5, 5, new GreedyEuclidean(), dungeon);
        Portal portal1 = new Portal(5, 6, dungeon, 1);
        Portal portal2 = new Portal(3, 3, dungeon, 1);
        dungeon.addEntity(enemy);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        enemy.moveDown();
        assertEquals(3, enemy.getX());
        assertEquals(3, enemy.getY());
    }
    @Test
    public void boulderTeleport(){
        Dungeon dungeon = new Dungeon(10, 10);
        Boulder boulder = new Boulder(5, 5, dungeon);
        Portal portal1 = new Portal(5, 6, dungeon, 1);
        Portal portal2 = new Portal(3, 3, dungeon, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        boulder.moveDown();
        assertEquals(3, boulder.getX());
        assertEquals(3, boulder.getY());
    }

}