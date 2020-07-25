package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    private Player player2;

    private Dungeon dungeon;

    private List<Entity> entities;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Treasure> treasures = new ArrayList<Treasure>();
    private ArrayList<Exit> exits = new ArrayList<Exit>();
    private ArrayList<FloorSwitch> floorswitches = new ArrayList<FloorSwitch>();

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayerList().get(0);
        if (dungeon.getPlayerList().size() > 1)
            this.player2 = dungeon.getPlayerList().get(1);
        else
            this.player2 = null;
        this.initialEntities = new ArrayList<>(initialEntities);
        entities = dungeon.getEntities();
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities){
            squares.getChildren().add(entity);
        }

        for (Entity entity : entities){
            if (entity instanceof Enemy){
                enemies.add((Enemy) entity);
            }else if (entity instanceof Treasure){
                treasures.add((Treasure) entity);
            }else if (entity instanceof Exit){
                exits.add((Exit) entity);
            }else if (entity instanceof FloorSwitch){
                floorswitches.add((FloorSwitch) entity);
            }
        }
        initGoal(dungeon.getGoal());
        for (Enemy enemy : enemies){
            enemy.setPlayer(player, player2);
            enemy.startMoving(player, player2);
        }
    }
    private void initGoal(Goal goal){
        if (goal instanceof ComplexGoal){
            ComplexGoal complexGoal = (ComplexGoal) goal;
            for (Goal goals: complexGoal.getGoals()){
                initGoal(goals);
            }
            return;
        }
        BasicGoal basicGoal = (BasicGoal) goal;
        String type = basicGoal.getName();
        switch(type){
            case "enemies":
                basicGoal.setGoalEntity(enemies);
                break;
            case "boulders":
                basicGoal.setGoalEntity(floorswitches);
                break;
            case "treasure":
                basicGoal.setGoalEntity(treasures);
                break;
            case "exit":
                basicGoal.setGoalEntity(exits);
                break;
        }
        return;
    }
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case W:
            if (player2 != null)
                player2.moveUp();
            break;
        case S:
            if (player2 != null)
                player2.moveDown();
            break;
        case A:
            if (player2 != null)
                player2.moveLeft();
            break;
        case D:
            if (player2 != null)
                player2.moveRight();
            break;
        default:
            break;
        }
    }

}

