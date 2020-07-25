package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class EnemyTest {
    @Test
    public void easyWalk() {
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        assertFalse(player.isDestroyed());
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        enemy.startMoving(player, null);
        enemy.setPlayer(player, null);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(player.isDestroyed());

    }
    @Test
    public void bigWall(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        dungeon.setPlayer(player);
        enemy.setPlayer(player, null);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);

        for (int i = 9; i > 0; i --){
            Wall wall = new Wall(5, i);
            dungeon.addEntity(wall);
        }
        enemy.startMoving(player, null);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(player.isDestroyed());
    }

    @Test
    public void killedByPotion(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(1, 0, new GreedyEuclidean(), dungeon);
        Potion potion = new Potion(0, 1);
        dungeon.setPlayer(player);
        enemy.setPlayer(player, null);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        dungeon.addEntity(potion);
        assertFalse(player.isDestroyed());
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        enemy.startMoving(player, null);
        player.moveDown();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.moveRight();
        player.moveRight();
        assertTrue(enemy.isDestroyed());
        assertFalse(player.isDestroyed());
    }
    @Test
    public void enemySuicidebySword(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        Enemy enemy = new Enemy(7, 7, new GreedyEuclidean(), dungeon);
        Weapon sword = new Weapon(5, 6);
        dungeon.addEntity(sword);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        enemy.setPlayer(player, null);
        dungeon.addEntity(enemy);
        player.moveDown();
        assertFalse(player.isDestroyed());
        assertFalse(enemy.getStrategy() instanceof EscapeStrategy);
        enemy.startMoving(player, null);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(enemy.isDestroyed());
        assertFalse(player.isDestroyed());
    }

    @Test
    public void playerSuicide(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        Enemy enemy = new Enemy(5, 6, new GreedyEuclidean(), dungeon);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        enemy.setPlayer(player, null);
        dungeon.addEntity(enemy);
        player.moveDown();
        assertTrue(player.isDestroyed());


    }

    @Test
    public void enemyMovements(){
        Dungeon dungeon = new Dungeon(1, 1);
        Enemy enemy = new Enemy(0, 0, new GreedyEuclidean(), dungeon);
        enemy.moveDown();
        assertEquals(0, enemy.getX());
        assertEquals(0, enemy.getY());
        enemy.moveLeft();
        assertEquals(0, enemy.getX());
        assertEquals(0, enemy.getY());
        enemy.moveRight();
        assertEquals(0, enemy.getX());
        assertEquals(0, enemy.getY());
        enemy.moveUp();
        assertEquals(0, enemy.getX());
        assertEquals(0, enemy.getY());
    
    }
}