package com.example.fastfingers.utils;

import com.example.fastfingers.controllers.GameController;
import com.example.fastfingers.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class EmirhanLib<T> {

    public static ArrayList<String> wordList = new ArrayList<>();

    private ArrayList<T> list;

    public EmirhanLib(T... objects) {
        list = new ArrayList<>();
        for (T o : objects) {
            list.add(o);
        }
    }

    public FXMLLoader showScreen(Stage stage, String fxml) throws IOException {
        stage = stage == null ? new Stage() : stage;
        String source = fxml.endsWith(".fxml") ? fxml : fxml + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(source));
        //HelloController test =  fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println(source);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }

    public ArrayList<T> getRangeElementsOfArray(int start, int end, ArrayList<T> arrayList) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(arrayList.get(i));
        }
        return list;
    }

    //Swaps b to a
    public void changeElements(EmirhanLib a, EmirhanLib b) {
        ArrayList<T> listToBeReplaced = (ArrayList<T>) a.getList().get(0);
        ArrayList<T> listToBeUsed = (ArrayList<T>) b.getList().get(0);
        System.out.println(listToBeReplaced.size());
        System.out.println(listToBeUsed.size());
        for (int i = 0; i < listToBeReplaced.size(); i++) {

            Label labelToBeSet = (Label) listToBeReplaced.get(i);
            Label labelToBeUsed = ((Label) listToBeUsed.get(i));
            System.out.println(labelToBeSet.getText() + "-----lower");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelToBeSet.setText(labelToBeUsed.getText());
                    labelToBeSet.setBackground(new Background(new BackgroundFill(null,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                }
            });
        }
        for (T temp : listToBeUsed) {
            Label labelToUpdate = (Label) temp;
            System.out.println(labelToUpdate.getText() + "---upper");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelToUpdate.setText(randomElement());
                }
            });

        }
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void loadWords() {
        try {

            File wordsFile = new File(MainApplication.class.getResource("words.yml").getFile());
            BufferedReader reader = new BufferedReader(new FileReader(wordsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.toLowerCase());
                System.out.println(line.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void display(GameController controller) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("EmirhanFingers");
                info.setHeaderText("Skor");
                info.setContentText("Doğru Kelime Sayınız: " + GameController.trueTypedWords + "\n" +
                        "Yanlış Kelime Sayınız: " + GameController.wrongTypedWords);
                Optional<ButtonType> type = info.showAndWait();
                if (type.get() == ButtonType.OK)
                    controller.setTimerLabel(60 + "");


            }
        });
    }

    /*public String readFromUrl(String url) {
        StringBuilder proccessedData = new StringBuilder();
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String data;

            while ((data = reader.readLine()) != null) {
                proccessedData.append(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proccessedData.toString();
    }*/

    public String randomElement() {
        Random r = new Random();
        return wordList.get(r.nextInt(wordList.size()));

    }

    public ArrayList<Label> addAll(ArrayList<Label> list, Label... objects) {
        ArrayList<Label> temp = (ArrayList<Label>) list.clone();
        for (Label label : objects) {
            temp.add(label);
        }
        return temp;
    }

}
