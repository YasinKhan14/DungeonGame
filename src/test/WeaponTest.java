package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class WeaponTest {

    @Test
    public void swordPickup(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Weapon weapon = new Weapon(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(weapon);
        assertFalse(player.hasSword());
        player.moveRight();
        assertTrue(weapon.isDestroyed());
        assertTrue(player.hasSword());
    }

    @Test
    public void enemySwordPickup(){
        Dungeon dungeon = new Dungeon(10, 10);
        Enemy enemy = new Enemy(0, 0, new GreedyEuclidean(), dungeon);
        Weapon weapon = new Weapon(1,0);
        dungeon.addEntity(enemy);
        dungeon.addEntity(weapon);
        enemy.moveRight();
        assertFalse(weapon.isDestroyed());
    }

    @Test
    public void swordPickupTwice(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Weapon weapon = new Weapon(1,0);
        Weapon weapon2 = new Weapon(2,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(weapon);
        dungeon.addEntity(weapon2);
        assertFalse(player.hasSword());
        player.moveRight();
        assertTrue(weapon.isDestroyed());
        assertTrue(player.hasSword());
        player.moveRight();
        assertTrue(weapon2.isDestroyed());
        
    }


    @Test
    public void chargeTest(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Weapon weapon = new Weapon(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(weapon);
        player.moveRight();
        assertTrue(player.hasSword());
        assertTrue(weapon.isDestroyed());
        assertTrue(player.hasSword());
        assertEquals(weapon.getCharges(), 5);
        player.weaponDecrement();
        assertEquals(weapon.getCharges(), 4);
        player.weaponDecrement();
        assertEquals(weapon.getCharges(), 3);
        player.weaponDecrement();
        assertEquals(weapon.getCharges(), 2);
        player.weaponDecrement();
        assertEquals(weapon.getCharges(), 1);
        player.weaponDecrement();
        assertEquals(weapon.getCharges(), 0);
        assertFalse(player.hasSword());

    }

    @Test
    public void destroyEnemyTriggerCharge(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(1, 1, new GreedyEuclidean(), dungeon);
        Weapon weapon = new Weapon(1,0);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        dungeon.addEntity(weapon);
        dungeon.addEntity(enemy);
        player.moveRight();
        assertTrue(player.hasSword());
        assertEquals(weapon.getCharges(), 5);
        assertFalse(enemy.isDestroyed());
        player.moveDown();
        assertEquals(weapon.getCharges(), 4);
        assertTrue(enemy.isDestroyed());

    }

}