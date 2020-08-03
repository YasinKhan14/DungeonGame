package unsw.dungeon;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScene {

    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;

    public DungeonScene(Stage stage, String map) throws IOException {

        this.stage = stage;
        if (map == null)
            map = "maze.json";
        title = map.split("\\.")[0].substring(0, 1).toUpperCase() + map.split("\\.")[0].substring(1);
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);

        controller = dungeonLoader.loadController();
        controller.setStage(stage);
        controller.setMap(map);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modded.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();


    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
