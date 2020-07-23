package test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class GoalTest {

    @Test
    public void noGoal(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure1 = new Treasure(1, 0);
        Treasure treasure2 = new Treasure(2, 0);
        Treasure treasure3 = new Treasure(3, 0);

        dungeon.setPlayer(player);
        dungeon.addEntity(player);

        dungeon.addEntity(treasure1);
        dungeon.addEntity(treasure2);
        dungeon.addEntity(treasure3);
        
        player.moveRight();
        player.moveRight();
        player.moveRight();



    }

    @Test
    public void simpleGoal(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure1 = new Treasure(1, 0);
        Treasure treasure2 = new Treasure(2, 0);
        Treasure treasure3 = new Treasure(3, 0);
        BasicGoal simpleGoal = new BasicGoal("collectAllTreasures");
        simpleGoal.attachGoalEntity(treasure1);
        simpleGoal.attachGoalEntity(treasure2);
        simpleGoal.attachGoalEntity(treasure3);
        dungeon.setPlayer(player);
        player.setGoal(simpleGoal);
        dungeon.addEntity(player);
        dungeon.addEntity(treasure1);
        dungeon.addEntity(treasure2);
        dungeon.addEntity(treasure3);
        
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        assertTrue(player.isCompleted());

    }

    @Test
    public void complexAndGoal(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(5, 0, new GreedyEuclidean(), dungeon);
        Treasure treasure1 = new Treasure(1, 0);
        Treasure treasure2 = new Treasure(2, 0);
        Treasure treasure3 = new Treasure(3, 0);
        Weapon weapon = new Weapon(4, 0);

        BasicGoal collectTreasures = new BasicGoal("collectAllTreasures");
        BasicGoal destroyEnemies = new BasicGoal("destroyAllEnemies");
        collectTreasures.attachGoalEntity(treasure1);
        collectTreasures.attachGoalEntity(treasure2);
        collectTreasures.attachGoalEntity(treasure3);
        destroyEnemies.attachGoalEntity(enemy);
        ComplexGoal complexAnd = new ComplexGoal("treasure&enemies", "and");
        complexAnd.attachGoal(destroyEnemies);
        complexAnd.attachGoal(collectTreasures);

        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        player.setGoal(complexAnd);
        dungeon.addEntity(treasure1);
        dungeon.addEntity(treasure2);
        dungeon.addEntity(treasure3);
        dungeon.addEntity(enemy);
        dungeon.addEntity(weapon);
        
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        player.moveRight();

        assertTrue(player.isCompleted());



    }

    @Test
    public void complexOrGoal(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(5, 0, new GreedyEuclidean(), dungeon);
        Treasure treasure1 = new Treasure(1, 0);
        Treasure treasure2 = new Treasure(2, 0);
        Treasure treasure3 = new Treasure(3, 0);
        Weapon weapon = new Weapon(4, 0);

        BasicGoal collectTreasures = new BasicGoal("collectAllTreasures");
        BasicGoal destroyEnemies = new BasicGoal("destroyAllEnemies");
        collectTreasures.attachGoalEntity(treasure1);
        collectTreasures.attachGoalEntity(treasure2);
        collectTreasures.attachGoalEntity(treasure3);
        destroyEnemies.attachGoalEntity(enemy);
        ComplexGoal complexOr = new ComplexGoal("treasure&enemies", "or");
        complexOr.attachGoal(destroyEnemies);
        complexOr.attachGoal(collectTreasures);

        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        player.setGoal(complexOr);
        dungeon.addEntity(treasure1);
        dungeon.addEntity(treasure2);
        dungeon.addEntity(treasure3);
        dungeon.addEntity(enemy);
        dungeon.addEntity(weapon);
        
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        assertFalse(player.isCompleted());
        player.moveRight();
        assertTrue(player.isCompleted());
        player.moveRight();
        player.moveRight();

        assertTrue(player.isCompleted());
    }

    
}