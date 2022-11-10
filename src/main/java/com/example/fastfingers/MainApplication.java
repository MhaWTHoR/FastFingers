package com.example.fastfingers;

import com.example.fastfingers.utils.EmirhanLib;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static EmirhanLib lib = new EmirhanLib();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = lib.showScreen(stage,"hello-view");
        lib.loadWords();
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        HelloController test = fxmlLoader.getController();
        test.onHelloButtonClick();*/
       // ((HelloController)loader.getController()).onHelloButtonClick();
    }

    public static void main(String[] args) {
        launch();
    }
}