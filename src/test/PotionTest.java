package test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

public class PotionTest {
    @Test
    public void playerGetPotion() {
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        Potion potion = new Potion(5, 6);
        dungeon.setPlayer(player);
        enemy.setPlayer(player, null);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        dungeon.addEntity(potion);
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        player.moveDown(); // player move to pick up potion
        // enemy has escape strategy as its movements
        assertTrue(enemy.getStrategy() instanceof EscapeStrategy);
        // potion lasts for 5 seconds
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // enemy doesn't have escape strategy as its movements
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        assertTrue(potion.isDestroyed());
    }
    @Test
    public void otherGetPotion(){
        Dungeon dungeon = new Dungeon(10, 10);
        Enemy enemy = new Enemy(5, 5, new GreedyEuclidean(), dungeon);
        Boulder boulder = new Boulder(6, 6, dungeon);
        Potion potion = new Potion(5, 6);
        dungeon.addEntity(enemy);
        dungeon.addEntity(potion);

        enemy.moveDown();
        assertFalse(potion.isDestroyed());
        boulder.moveLeft();
        assertFalse(potion.isDestroyed());

    }
    @Test
    public void appendPotion(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        Potion potion = new Potion(5, 6);
        Potion potion2 = new Potion (5, 7);
        dungeon.setPlayer(player);
        enemy.setPlayer(player, null);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        dungeon.addEntity(potion);
        dungeon.addEntity(potion2);
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        player.moveDown(); // player move to pick up potion
        // enemy has escape strategy as its movements
        assertTrue(enemy.getStrategy() instanceof EscapeStrategy);
        // potion lasts for 5 seconds
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.moveDown();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // enemy doesn't have escape strategy as its movements
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        assertTrue(potion.isDestroyed());
    }
}