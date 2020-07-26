/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Entity>[][] map;
    private List<Player> playerList;
    private Goal goal;
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.map = new ArrayList[height][width];
        for (int i = 0; i < height; i ++){
            for (int j = 0; j < width; j ++){
                this.map[i][j] = new ArrayList<Entity>();
            }
        }
        this.playerList = new ArrayList<Player>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Entity>[][] getMap(){
        return map;
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public void setPlayer(Player player) {
        playerList.add(player);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        map[entity.getY()][entity.getX()].add(entity);
    }

    public void updateMap(Entity entity, int x, int y){
        map[entity.getY()][entity.getX()].remove(entity);
        entity.x().set(x);
        entity.y().set(y);
        map[entity.getY()][entity.getX()].add(entity);
    }

    public void removeFromMap(Entity entity){
        map[entity.getY()][entity.getX()].remove(entity);
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }



}
