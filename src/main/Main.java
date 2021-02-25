package main;

import controllerview.TelephonelistC;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author: Oliver Steiner
 * @version: 1, 24.02.2021
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        TelephonelistC.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
