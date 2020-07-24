package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainController {


    @FXML
    private Button selectButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button playButton;

    private DungeonScene dungeonScene;

    private Stage stage;

    private String map;

    @FXML
    void helpHandler(ActionEvent event) {
        
    }

    @FXML
    public void playHandler(ActionEvent event) throws IOException{
        dungeonScene = new DungeonScene(stage, map);
        dungeonScene.start();
    }

    @FXML
    public void selectHandler(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Map");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Dungeon JSON file", "*.json"));
        //fileChooser.setInitialDirectory(new File("../../dungeons"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        map = selectedFile.getName();
        dungeonScene = new DungeonScene(stage, map);
        dungeonScene.start();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void initialize() {

    }

}
