package com.example.fastfingers.controllers;

import com.example.fastfingers.utils.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static com.example.fastfingers.MainApplication.lib;

public class GameController {

    @FXML
    private Label upperLabel1, upperLabel2, upperLabel3, upperLabel4, upperLabel5,
            lowerLabel1, lowerLabel2, lowerLabel3, lowerLabel4, lowerLabel5, timerLabel;
    @FXML
    TextField textField;
    boolean gameIsOn = false;
    static ArrayList<Label> listOfLabels = new ArrayList<>();
    public static int whichLabelSelected=0;
    public static int trueTypedWords = 0;
    public static int wrongTypedWords = 0;

    public void setTimerLabel(String str) {
        timerLabel.setText(str);
    }


    @FXML
    public TextField returnTextFieldObject() {
        return textField;
    }

    @FXML
    public void getThingsDone() {
        listOfLabels = lib.addAll(listOfLabels, upperLabel1, upperLabel2,
                upperLabel3, upperLabel4, upperLabel5, lowerLabel1, lowerLabel2,
                lowerLabel3, lowerLabel4, lowerLabel5);
        upperLabel1.setText(lib.randomElement());
        upperLabel2.setText(lib.randomElement());
        upperLabel3.setText(lib.randomElement());
        upperLabel4.setText(lib.randomElement());
        upperLabel5.setText(lib.randomElement());
        lowerLabel1.setText(lib.randomElement());
        lowerLabel2.setText(lib.randomElement());
        lowerLabel3.setText(lib.randomElement());
        lowerLabel4.setText(lib.randomElement());
        lowerLabel5.setText(lib.randomElement());
    }

    @FXML
    public boolean startGameIfNotStarted() {
        if (!gameIsOn) {
            gameIsOn = true;
            Thread thread = new Timer(this);
            thread.start();
        }
        return gameIsOn;
    }

    @FXML
    public void resetTheGame() {
        whichLabelSelected = 0;
        wrongTypedWords = 0;
        trueTypedWords = 0;
        gameIsOn = false;
    }


}
