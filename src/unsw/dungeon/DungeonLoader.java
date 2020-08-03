package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private Goal goal;
    private List<Enemy> enemies;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        enemies = new ArrayList<Enemy>();
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        goal = loadGoal(jsonGoals);
        dungeon.setGoal(goal);
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        for (Enemy enemy : enemies){
            onLoad(enemy);
        }
        return dungeon;
    }

    private Goal loadGoal(JSONObject json){
        String name = json.getString("goal");
        Goal resultGoal = null;
        Goal subGoal1;
        Goal subGoal2;
        JSONArray jsonArray;
        switch (name){
            case "AND":
                ComplexGoal complexAND = new ComplexGoal("and");
                jsonArray = json.getJSONArray("subgoals");
                subGoal1 = loadGoal(jsonArray.getJSONObject(0));
                subGoal2 = loadGoal(jsonArray.getJSONObject(1));
                complexAND.attachGoal(subGoal1);
                complexAND.attachGoal(subGoal2);
                resultGoal = complexAND;
                break;
            case "OR":
                ComplexGoal complexOR = new ComplexGoal("or");
                jsonArray = json.getJSONArray("subgoals");
                subGoal1 = loadGoal(jsonArray.getJSONObject(0));
                subGoal2 = loadGoal(jsonArray.getJSONObject(1));
                complexOR.attachGoal(subGoal1);
                complexOR.attachGoal(subGoal2);
                resultGoal = complexOR;
                break;
            case "enemies":
                BasicGoal basicEnemies = new BasicGoal("enemies");
                resultGoal = basicEnemies;
                break;
            case "boulders":
                BasicGoal basicBoulders = new BasicGoal("boulders");
                resultGoal = basicBoulders;
                break;
            case "treasure":
                BasicGoal basicTreasure = new BasicGoal("treasure");
                resultGoal = basicTreasure;
                break;
            case "exit":
                BasicGoal basicExit = new BasicGoal("exit");
                resultGoal = basicExit;
                break;
        }
        return resultGoal;
    }
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "sword":
            Weapon sword = new Weapon(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "invincibility":
            Potion potion = new Potion(x, y);
            onLoad(potion);
            entity = potion;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "enemy":
            Enemy enemy = new Enemy(x, y, new Astar(dungeon), dungeon);
            enemies.add(enemy);
            entity = enemy;
            break;
        case "ghostEnemy":
            GhostEnemy genemy = new GhostEnemy(x, y, new Astar(dungeon), dungeon);
            enemies.add(genemy);
            entity = genemy;
            break;
        case "switch":
            FloorSwitch floorSwitch = new FloorSwitch(x, y);
            onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x, y, dungeon);
            onLoad(boulder);
            entity = boulder;
            break;
        case "key":
            id = json.getInt("id");
            Key key = new Key(x, y, id);
            onLoad(key);
            entity = key;
            break;
        case "door":
            id = json.getInt("id");
            Door door = new Door(x, y, id);
            onLoad(door);
            entity = door;
            break;
        case "portal":
            id = json.getInt("id");
            Portal portal = new Portal(x, y, dungeon, id);
            onLoad(portal);
            entity = portal;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        }
        if (entity == null)
            return;
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);
    
    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Weapon sword);

    public abstract void onLoad(Potion potion);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(GhostEnemy genemy);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Exit exit);
}
