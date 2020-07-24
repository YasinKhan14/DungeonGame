package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button selectButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button playButton;

    private DungeonScene dungeonScene;

    @FXML
    void helpHandler(ActionEvent event) {
        
    }

    @FXML
    public void playHandler(ActionEvent event) {
        dungeonScene.start();
    }

    @FXML
    public void selectHandler(ActionEvent event) {

    }

    public void setDungeonScene(DungeonScene dungeonScene) {
        this.dungeonScene = dungeonScene;
    }

}
