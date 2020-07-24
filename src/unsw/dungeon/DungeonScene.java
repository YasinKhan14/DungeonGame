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
        title = "Game Screen";
        if (map == null)
            map = "maze.json";
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);

        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
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
