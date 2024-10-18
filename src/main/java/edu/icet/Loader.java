package edu.icet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Loader extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/homepage.fxml"))));
        stage.setOnCloseRequest(event-> {
            System.exit(0);
        });
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.setTitle("Homepage");
        stage.show();
    }
}
