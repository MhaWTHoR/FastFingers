package com.example.fastfingers.utils;

import com.example.fastfingers.controllers.GameController;
import javafx.application.Platform;

public class Timer extends Thread {

    public static int timer = 3;

    private GameController controller;

    public Timer(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (timer > 0) {
            timer--;
            System.out.println();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.setTimerLabel(timer+"");
                }
            });
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        EmirhanLib.display(controller);
        controller.resetTheGame();


    }
}
