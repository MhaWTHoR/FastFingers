package com.example.fastfingers.controllers;

import com.example.fastfingers.utils.EmirhanLib;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
    EmirhanLib lib = new EmirhanLib();
    @FXML
    private Label welcomeText;

    //ÇÖZÜLMESİ GEREKEN: JavaFX farklı bir threadden gui düzenlemeye müsade etmiyor.Platform.runLater metodu da komple
    // programı donduruyor.Buna bir çözüm bul.
    @FXML
    protected void onHelloButtonClick() throws IOException {

        ((Stage) welcomeText.getScene().getWindow()).close();
        FXMLLoader loader = lib.showScreen(null, "game.fxml");

        GameController controller = loader.getController();
        controller.getThingsDone();

        controller.textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                KeyCode code = keyEvent.getCode();

                Label selectedLabel = GameController.listOfLabels.get(GameController.whichLabelSelected);
                String labelText = selectedLabel.getText();

                System.out.println(selectedLabel.getText() + "|||" + controller.textField.getText() + "||");
                System.out.println(labelText.contains(controller.textField.getText()));
                String textFieldText = controller.textField.getText().replaceAll(" ", "");


                //Coloring code block
                if (!labelText.contains(textFieldText)) {
                    selectedLabel.setBackground(new Background
                            (new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                } else
                    selectedLabel.setBackground(new Background
                            (new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

                TextField typedArea = controller.textField;
                if (code == KeyCode.SPACE) {

                    GameController.whichLabelSelected++;
                    if (GameController.whichLabelSelected == 5) {
                        EmirhanLib<ArrayList<Label>> wordLabelToReplace = new EmirhanLib<>(
                                lib.getRangeElementsOfArray(0,5,
                                GameController.listOfLabels));
                        EmirhanLib<ArrayList<Label>> wordLabelToUpdate = new EmirhanLib<>(
                                lib.getRangeElementsOfArray(5,GameController.listOfLabels.size(),
                                        GameController.listOfLabels)
                        );
                        GameController.whichLabelSelected = 0;
                        lib.changeElements(wordLabelToReplace,wordLabelToUpdate);
                    }
                    typedArea.setText("");
                    if (labelText.equals(textFieldText)) {
                        System.out.println("test");
                        GameController.trueTypedWords++;
                        selectedLabel.setBackground(new Background(new BackgroundFill(Color.GREEN,
                                CornerRadii.EMPTY, Insets.EMPTY)));

                    } else
                        GameController.wrongTypedWords++;

                }
            }
        });


    }
}