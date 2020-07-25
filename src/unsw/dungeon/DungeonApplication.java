package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;


public class DungeonApplication extends Application {

    public void start(Stage primaryStage) throws IOException {

        MainScreen mainScreen = new MainScreen(primaryStage);
        mainScreen.start();
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
