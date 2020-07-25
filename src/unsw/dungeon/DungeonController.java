package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Stage stage;
    private List<Entity> entities;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Treasure> treasures = new ArrayList<Treasure>();
    private ArrayList<Exit> exits = new ArrayList<Exit>();
    private ArrayList<FloorSwitch> floorswitches = new ArrayList<FloorSwitch>();

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
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

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }

        for (Entity entity : entities) {
            if (entity instanceof Enemy) {
                enemies.add((Enemy) entity);
            } else if (entity instanceof Treasure) {
                treasures.add((Treasure) entity);
            } else if (entity instanceof Exit) {
                exits.add((Exit) entity);
            } else if (entity instanceof FloorSwitch) {
                floorswitches.add((FloorSwitch) entity);
            }
        }
        initGoal(dungeon.getGoal());
        for (Enemy enemy : enemies) {
            enemy.setPlayer(player);
            enemy.startMoving();
        }
    }

    private void initGoal(Goal goal) {
        if (goal instanceof ComplexGoal) {
            ComplexGoal complexGoal = (ComplexGoal) goal;
            for (Goal goals : complexGoal.getGoals()) {
                initGoal(goals);
            }
            return;
        }
        BasicGoal basicGoal = (BasicGoal) goal;
        String type = basicGoal.getName();
        switch (type) {
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
            case P:
                for (Enemy enemy : enemies) {
                    enemy.stopMoving();
                }
                squares.setEffect(new GaussianBlur());
                VBox pauseRoot = new VBox(5);
                pauseRoot.getChildren().add(new Label("Paused"));
                pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
                pauseRoot.setAlignment(Pos.CENTER);
                pauseRoot.setPadding(new Insets(20));

                Button resume = new Button("Resume");
                pauseRoot.getChildren().add(resume);
                Button back = new Button("Main menu");
                pauseRoot.getChildren().add(back);

                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                popupStage.initOwner(stage);
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));
                resume.setOnAction(e -> {
                    squares.setEffect(null);
                    popupStage.hide();
                    for (Enemy enemy : enemies) {
                        enemy.startMoving();
                    }
                });
                back.setOnAction(e -> {
                    MainScreen main;
                    try {
                        main = new MainScreen(stage);
                        popupStage.hide();
                        main.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });

                popupStage.show();
        default:
            break;
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}

