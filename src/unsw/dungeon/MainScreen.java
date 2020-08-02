package unsw.dungeon;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreen {

    private Stage stage;
    private String title;
    private MainController controller;
    private Scene scene;

    public MainScreen(Stage stage) throws IOException {

        this.stage = stage;
        title = "Game Screen";
        controller = new MainController();
        controller.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));

        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        //root.requestFocus();
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}


