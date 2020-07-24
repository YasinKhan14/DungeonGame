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

    @FXML
    void helpHandler(ActionEvent event) {
        
    }

    @FXML
    void playHandler(ActionEvent event) {
        DungeonApplication.start();
    }

    @FXML
    void selectHandler(ActionEvent event) {

    }

}
